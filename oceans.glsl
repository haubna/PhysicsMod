PHYSICS MOD OCEAN V3 SHADERPACK API (v188 and above)
THIS FILE IS PUBLIC DOMAIN SO USE IT HOWEVER YOU WANT!

Put these calls directly in the shaderpack's normal gbuffers_water and
shadow stages. Do not add a separate versioned ocean file or include a
Physics Mod file. Physics Mod detects the PHYSICS_OCEAN_V3 references and supplies
all functions needed. It adds two functions for the vertex stage and one
for the fragment stage. This will add support for both ocean rendering techniques.

VERTEX STAGE - gbuffers_water.vsh and shadow.vsh
#ifdef PHYSICS_OCEAN_V3
  vec4 finalPosition = physics_oceanVertex(gl_Vertex);
#endif

those are the two function signatures available
vec3 physics_oceanVertex(const in vec3 position);
vec4 physics_oceanVertex(const in vec4 position);

FRAGMENT STAGE - gbuffers_water.fsh and shadow.fsh
#ifdef PHYSICS_OCEAN_V3
  PhysicsOceanData ocean = physics_oceanFragment();
#endif

Available fields:
  ocean.normal        final world-space surface normal, including ripples
  ocean.foam          final foam blend (0..1)

If a pack does not reference PHYSICS_OCEAN_V3 in its water/shadow source,
Physics Mod keeps the existing automatic ShaderInjectionOcean fallback.
