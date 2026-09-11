# Physics Mod

Welcome to the official repository for **Physics Mod**.

Found a problem? Feel free to [report a bug](../../issues) or open an issue with feedback.

---

## Shaders

### Ocean Physics 3.0

Starting with **Physics Mod Pro v170**, shaderpack developers can create and include custom ocean shaders. The new v3 of Ocean Physics is only supported by **Physics Mod Pro v188** and upwards. Support for Ocean Physics v2 got entirely replaced by v3 for simplicity reasons.

> [!NOTE]
> Custom ocean shaders are currently supported only when using **Iris**.

Check out [`oceans.glsl`](https://github.com/haubna/PhysicsMod/blob/main/oceans.glsl) on how to add support. There is one function for the vertex stage and one for the fragment stage that you have to add. Custom tessellation stages are currently not supported (it generates them dynamically). If you want to support versions prior to v188 check out [`this`](https://github.com/haubna/PhysicsMod/blob/main/LEGACY_OCEAN.md).

---

### Snow

Snow is rendered through the entity shader and uses the following entity ID:

```glsl
uniform int entityId;

// Snow entity ID
829925
```

Minecraft 26.2 and later only support it via the `mc_Entity` attribute. It also uses `829925` as ID.

---

## Ragdoll API

> Available in **Physics Mod 2.2.3 and newer**.

The Ragdoll API allows you to add ragdoll physics to entities from your own mod.

### Requirements

Your custom entities must use Minecraft's built-in `ModelPart` class.

> [!WARNING]
> Do not replace `ModelPart` with a custom implementation. Physics Mod hooks directly into this class to create the ragdoll physics.

Create a `RagdollHook` and register it using:

```java
RagdollMapper.addHook(ragdollHook);
```

A complete `RagdollHook` example is available in this repository.

### `RagdollHook` methods

#### `filterCuboidsFromEntities`

Use this method to remove excessive or duplicated cubes from an entity model.

Some Minecraft features and rendering overlays can generate multiple cubes for the same model part. Without filtering, these may result in duplicated physics objects or multiple cube drops.

#### `map`

Use this method to define the connections between the model's cubes.

When you are unsure which model parts should be connected, print the available model-part information with:

```java
RagdollMapper.printModelParts(model);
```

---

## Credits

Special thanks to **fabmax** for creating the PhysX JNI wrapper:

[github.com/fabmax/physx-jni](https://github.com/fabmax/physx-jni)

---

## Links

* [Official Physics Mod website](https://minecraftphysicsmod.com)
* [Report bugs and issues](../../issues)
