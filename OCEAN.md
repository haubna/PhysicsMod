# Physics Mod Ocean V3 Shaderpack API

**Requires Physics Mod v188 or above.**

**This file is public domain, so use it however you want!**

Put these calls directly in the shaderpack's normal `gbuffers_water` and
`shadow` stages. Do not add a separate versioned ocean file or include a
Physics Mod file. Physics Mod detects the `PHYSICS_OCEAN_V3` references and
supplies all functions needed: two functions for the vertex stage and one
for the fragment stage. This adds support for both ocean rendering techniques.

## Vertex stage

Files: `gbuffers_water.vsh` and `shadow.vsh`

```glsl
#ifdef PHYSICS_OCEAN_V3
  vec4 finalPosition = physics_oceanVertex(gl_Vertex);
#endif
```

Available function signatures:

```glsl
vec3 physics_oceanVertex(const in vec3 position);
vec4 physics_oceanVertex(const in vec4 position);
```

## Fragment stage

Files: `gbuffers_water.fsh` and `shadow.fsh`

```glsl
#ifdef PHYSICS_OCEAN_V3
  PhysicsOceanData ocean = physics_oceanFragment();
#endif
```

### Available fields

| Field | Description |
| --- | --- |
| `ocean.normal` | Final world-space surface normal, including ripples. |
| `ocean.foam` | Final foam blend (`0..1`). |

## Fallback behavior

If a pack does not reference `PHYSICS_OCEAN_V3` in its water/shadow source,
Physics Mod keeps the existing automatic `ShaderInjectionOcean` fallback.
