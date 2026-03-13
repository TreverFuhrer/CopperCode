# Copper Code Architecture

This scaffold keeps common logic in `src/main/java` and client-only code in `src/client/java`.
The goal is to give Copper Code a stable registration and expansion point without pretending gameplay systems already exist.

## Layout

- `trev.coppercode.CopperCode`: common Fabric entrypoint that initializes shared registries.
- `trev.coppercode.CopperCodeClient`: client Fabric entrypoint that initializes renderers, screens, and client networking.
- `trev.coppercode.CopperCodeDataGenerator`: placeholder datagen entrypoint for generated assets and data providers.
- `trev.coppercode.registry`: common registration classes for blocks, items, entities, block entities, screen handlers, data components, networking, sounds, commands, and item groups.
- `trev.coppercode.util`: small shared helpers for constants, identifiers, and logging.
- `trev.coppercode.client.registry`: client-only registration classes for screens and renderers.
- `trev.coppercode.client.network`: client-only packet handlers.

## Initialization Flow

1. `CopperCode` runs on mod initialization and wires all common registries in one place.
2. `CopperCodeClient` runs only on the client and wires client-only systems.
3. `CopperCodeDataGenerator` owns future datagen providers once blocks, items, tags, recipes, and models are added.

## Expansion Guidelines

- Add real content registrations to the matching `Mod*` class instead of growing `CopperCode` directly.
- Keep gameplay state and networking payload definitions in common code unless they are purely visual.
- Keep screen classes, renderers, and client packet receivers in `src/client/java`.
- Add datagen providers only when a real content system exists, and register them from `CopperCodeDataGenerator`.
- Add scripting, golem runtime, and editor packages as separate top-level feature areas when their design is ready.
