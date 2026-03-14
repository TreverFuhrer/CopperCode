package trev.coppercode.client.render.entity;

import java.util.Optional;
import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.golem.CopperGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BlockDecorationLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.CopperGolemRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.golem.CopperGolem;
import net.minecraft.world.entity.animal.golem.CopperGolemOxidationLevels;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.BlockItemStateProperties;
import trev.coppercode.entity.ProgrammableCopperGolemEntity;

/** Direct renderer copy of the vanilla copper golem wired to the programmable entity type. */
@Environment(EnvType.CLIENT)
public final class ProgrammableCopperGolemRenderer
	extends MobRenderer<ProgrammableCopperGolemEntity, CopperGolemRenderState, CopperGolemModel> {
	public ProgrammableCopperGolemRenderer(EntityRendererProvider.Context context) {
		super(context, new CopperGolemModel(context.bakeLayer(ModelLayers.COPPER_GOLEM)), 0.5F);
		this.addLayer(
			new LivingEntityEmissiveLayer<>(
				this,
				getEyeTextureLocationProvider(),
				(renderState, partialTick) -> 1.0F,
				new CopperGolemModel(context.bakeLayer(ModelLayers.COPPER_GOLEM)),
				RenderTypes::eyes,
				false
			)
		);
		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(new BlockDecorationLayer<>(this, renderState -> renderState.blockOnAntenna, this.model::applyBlockOnAntennaTransform));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
	}

	@Override
	public Identifier getTextureLocation(CopperGolemRenderState renderState) {
		return CopperGolemOxidationLevels.getOxidationLevel(renderState.weathering).texture();
	}

	@Override
	public CopperGolemRenderState createRenderState() {
		return new CopperGolemRenderState();
	}

	@Override
	public void extractRenderState(ProgrammableCopperGolemEntity entity, CopperGolemRenderState renderState, float partialTick) {
		super.extractRenderState(entity, renderState, partialTick);
		ArmedEntityRenderState.extractArmedEntityRenderState(entity, renderState, this.itemModelResolver, partialTick);
		renderState.weathering = entity.getWeatherState();
		renderState.copperGolemState = entity.getState();
		renderState.idleAnimationState.copyFrom(entity.getIdleAnimationState());
		renderState.interactionGetItem.copyFrom(entity.getInteractionGetItemAnimationState());
		renderState.interactionGetNoItem.copyFrom(entity.getInteractionGetNoItemAnimationState());
		renderState.interactionDropItem.copyFrom(entity.getInteractionDropItemAnimationState());
		renderState.interactionDropNoItem.copyFrom(entity.getInteractionDropNoItemAnimationState());
		renderState.blockOnAntenna = Optional.of(entity.getItemBySlot(CopperGolem.EQUIPMENT_SLOT_ANTENNA))
			.flatMap(
				itemStack -> {
					if (itemStack.getItem() instanceof BlockItem blockItem) {
						BlockItemStateProperties properties = (BlockItemStateProperties)itemStack.getOrDefault(
							DataComponents.BLOCK_STATE,
							BlockItemStateProperties.EMPTY
						);
						return Optional.of(properties.apply(blockItem.getBlock().defaultBlockState()));
					}

					return Optional.empty();
				}
			);
	}

	private static Function<CopperGolemRenderState, Identifier> getEyeTextureLocationProvider() {
		return renderState -> CopperGolemOxidationLevels.getOxidationLevel(renderState.weathering).eyeTexture();
	}
}
