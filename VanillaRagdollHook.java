package net.diebuddies.physics.ragdoll;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.diebuddies.physics.PhysicsEntity;
import net.diebuddies.physics.ragdoll.RagdollMapper.Counter;
import net.diebuddies.physics.ragdoll.RagdollMapper.ModelPartIndex;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.ambient.BatModel;
import net.minecraft.client.model.animal.allay.AllayModel;
import net.minecraft.client.model.animal.armadillo.ArmadilloModel;
import net.minecraft.client.model.animal.axolotl.AxolotlModel;
import net.minecraft.client.model.animal.bee.BeeModel;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.animal.chicken.ChickenModel;
import net.minecraft.client.model.animal.dolphin.DolphinModel;
import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.model.animal.equine.HorseModel;
import net.minecraft.client.model.animal.feline.FelineModel;
import net.minecraft.client.model.animal.fish.CodModel;
import net.minecraft.client.model.animal.fish.PufferfishBigModel;
import net.minecraft.client.model.animal.fish.PufferfishMidModel;
import net.minecraft.client.model.animal.fish.PufferfishSmallModel;
import net.minecraft.client.model.animal.fish.SalmonModel;
import net.minecraft.client.model.animal.fish.TropicalFishLargeModel;
import net.minecraft.client.model.animal.fish.TropicalFishSmallModel;
import net.minecraft.client.model.animal.fox.FoxModel;
import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.animal.frog.TadpoleModel;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.animal.goat.GoatModel;
import net.minecraft.client.model.animal.golem.CopperGolemModel;
import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.animal.llama.LlamaModel;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.model.animal.sheep.SheepModel;
import net.minecraft.client.model.animal.sniffer.SnifferModel;
import net.minecraft.client.model.animal.squid.SquidModel;
import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.model.monster.creaking.CreakingModel;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.model.monster.dragon.EnderDragonModel;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.model.monster.endermite.EndermiteModel;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.model.monster.hoglin.HoglinModel;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.model.monster.phantom.PhantomModel;
import net.minecraft.client.model.monster.piglin.AbstractPiglinModel;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.model.monster.ravager.RavagerModel;
import net.minecraft.client.model.monster.silverfish.SilverfishModel;
import net.minecraft.client.model.monster.skeleton.BoggedModel;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.model.monster.strider.StriderModel;
import net.minecraft.client.model.monster.vex.VexModel;
import net.minecraft.client.model.monster.warden.WardenModel;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.model.monster.wither.WitherBossModel;
import net.minecraft.client.model.monster.zombie.DrownedModel;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.model.monster.zombie.ZombieVillagerModel;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.model.object.armorstand.ArmorStandModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.BeeStingerLayer;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.Deadmau5EarsLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.client.renderer.entity.layers.SimpleEquipmentLayer;
import net.minecraft.client.renderer.entity.layers.SpinAttackEffectLayer;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.ArmadilloRenderState;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.client.renderer.entity.state.GoatRenderState;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.skeleton.Bogged;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

public class VanillaRagdollHook implements RagdollHook {

	@Override
	public void map(Ragdoll ragdoll, Entity entity, EntityModel model, EntityRenderState renderState) {
		if (model instanceof CreakingModel creaking) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_arm").index;
			int leftArmOffset = indices.get("left_arm").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);

			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof AbstractPiglinModel piglin) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_arm").index;
			int leftArmOffset = indices.get("left_arm").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			int leftEarOffset = indices.get("left_ear").index;
			int rightEarOffset = indices.get("right_ear").index;

			ragdoll.addConnection(leftEarOffset, headOffset);
			ragdoll.addConnection(rightEarOffset, headOffset);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(leftArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			int leftPantsOffset = indices.get("left_pants").index;
			int rightPantsOffset = indices.get("right_pants").index;
			int leftSleeveOffset = indices.get("left_sleeve").index;
			int rightSleeveOffset = indices.get("right_sleeve").index;
			int jacketOffset = indices.get("jacket").index;

			ragdoll.addConnection(leftPantsOffset, leftLegOffset, true, true);
			ragdoll.addConnection(rightPantsOffset, rightLegOffset, true, true);
			ragdoll.addConnection(leftSleeveOffset, leftArmOffset, true, true);
			ragdoll.addConnection(rightSleeveOffset, rightArmOffset, true, true);
			ragdoll.addConnection(jacketOffset, bodyOffset, true, true);

			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof VexModel vex) {
			int headOffset = 0;
			int bodyOffset = 1;
			int clothOffset = 2;
			int rightArmOffset = 3;
			int leftArmOffset = 4;
			int rightWingOffset = 5;
			int leftWingOffset = 6;

			ragdoll.addConnection(headOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(clothOffset, bodyOffset, true);
			ragdoll.addConnection(rightArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(leftArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(rightWingOffset, bodyOffset, true);
			ragdoll.addConnection(leftWingOffset, bodyOffset, true);
			
			RagdollMapper.getCuboids(ragdoll, vex.root(), new Counter());
		} else if (model instanceof HumanoidModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			// basic ragdoll for humans/zombies
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_arm").index;
			int leftArmOffset = indices.get("left_arm").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);

			int hatOffset = indices.get("hat").index;
			
			if (model instanceof PlayerModel) {
				PlayerModel playerModel = (PlayerModel) model;
				AvatarRenderState playerState = (AvatarRenderState) renderState;
				
				try {
					int leftPantsOffset = indices.get("left_pants").index;
					int rightPantsOffset = indices.get("right_pants").index;
					int leftSleeveOffset = indices.get("left_sleeve").index;
					int rightSleeveOffset = indices.get("right_sleeve").index;
					int jacketOffset = indices.get("jacket").index;
					
					if (playerState.showLeftPants) {
						ragdoll.addConnection(leftPantsOffset, leftLegOffset, true, true);
					}

					if (playerState.showRightPants) {
						ragdoll.addConnection(rightPantsOffset, rightLegOffset, true, true);
					}

					if (playerState.showLeftSleeve) {
						ragdoll.addConnection(leftSleeveOffset, leftArmOffset, true, true);
					}

					if (playerState.showRightSleeve) {
						ragdoll.addConnection(rightSleeveOffset, rightArmOffset, true, true);
					}

					if (playerState.showJacket) {
						ragdoll.addConnection(jacketOffset, bodyOffset, true, true);
					}

					if (playerState.showHat) {
						ragdoll.addConnection(hatOffset, headOffset, true, true);
					}
				} catch (Exception e) {}
			} else if (model instanceof ArmorStandModel) {
				try {
					int rightBodyStickOffset = indices.get("right_body_stick").index;
					int leftBodyStickOffset = indices.get("left_body_stick").index;
					int shoulderStickOffset = indices.get("shoulder_stick").index;
					int basePlateOffset = indices.get("base_plate").index;
					ragdoll.addConnection(rightBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(leftBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(shoulderStickOffset, bodyOffset, true);
				} catch (Exception e) {}
			} else if (model instanceof BoggedModel) {
				boolean sheared = false;

				if (entity instanceof Bogged bogged) {
					sheared = bogged.isSheared();
				}
				
				// attach bow
				PhysicsEntity bow = null;
				
				for (int i = 0; i < ragdoll.bodies.size(); i++) {
					PhysicsEntity b = ragdoll.bodies.get(i);
					
					if (b.feature instanceof ItemInHandLayer) {
						bow = ragdoll.bodies.remove(i);
						break;
					}
				}
				
				int count = RagdollMapper.countModelParts(entity, model);
				
				if (sheared) {
					if (count < ragdoll.bodies.size())
						ragdoll.addOverlayConnections(true, count * 2, 0, 2);
				} else {
					// mushrooms are from 1 to 6 so we have to add the overlay like that 
					// when the bogged has mushrooms
					ragdoll.addConnection(13, 0, true, true);
					ragdoll.addConnection(14, 7, true, true);
					ragdoll.addConnection(15, 8, true, true);
					ragdoll.addConnection(16, 9, true, true);
					ragdoll.addConnection(17, 10, true, true);
					ragdoll.addConnection(18, 11, true, true);
					ragdoll.addConnection(19, 12, true, true);
					
					// add the mushrooms
					ragdoll.addConnection(1, 0, true, true);
					ragdoll.addConnection(2, 0, true, true);
					ragdoll.addConnection(3, 0, true, true);
					ragdoll.addConnection(4, 0, true, true);
					ragdoll.addConnection(5, 0, true, true);
					ragdoll.addConnection(6, 0, true, true);
				}
				
				ragdoll.addConnection(hatOffset, headOffset, true, true);
				
				if (bow != null) {
					ragdoll.bodies.add(bow);
					ragdoll.addConnection(ragdoll.bodies.size() - 1, rightArmOffset, true, true);
				}
			} else if (model instanceof SkeletonModel) {
				// attach bow
				// currently all bows get removed (in filterCuboidsFromEntities) due to some skeletons
				// dropping a bow, duplicate bows would look odd
				PhysicsEntity bow = null;
				
				for (int i = 0; i < ragdoll.bodies.size(); i++) {
					PhysicsEntity b = ragdoll.bodies.get(i);
					
					if (b.feature instanceof ItemInHandLayer) {
						bow = ragdoll.bodies.remove(i);
						break;
					}
				}

				ragdoll.addConnection(hatOffset, headOffset, true, true);
				
				// this adds support for strays
				int count = RagdollMapper.countModelParts(entity, model);
				
				if (count < ragdoll.bodies.size())
					ragdoll.addOverlayConnections(true, count * 2, 0, 2);
				
				if (bow != null) {
					ragdoll.bodies.add(bow);
					ragdoll.addConnection(ragdoll.bodies.size() - 1, rightArmOffset, true, true);
				}
			} else if (model instanceof DrownedModel) {
				int count = RagdollMapper.countModelParts(entity, model);
				ragdoll.addConnection(hatOffset, headOffset, true, true);

				if (ragdoll.bodies.size() > count * 2) {
					// with trident there gets something rendered before the overlay
					// so offset the indices by 1
					ragdoll.addOverlayConnections(true, 14, 5, 2);
					
					// trident ragdoll
					int base = 7;
					int spike1 = 8;
					int spike2 = 9;
					int spike3 = 10;
					int base2 = 11;
					ragdoll.addConnection(base2, base, true);
					ragdoll.addConnection(spike1, base, true);
					ragdoll.addConnection(spike2, base, true);
					ragdoll.addConnection(spike3, base, true);
				} else {
					if (count < ragdoll.bodies.size())
						ragdoll.addOverlayConnections(true);
				}
			} else if (model instanceof EndermanModel) {
				ragdoll.addConnection(hatOffset, headOffset, true, true);
				ragdoll.addOverlayConnections(true);
			} else if (model instanceof ZombieVillagerModel) {
				ragdoll.addConnection(hatOffset, headOffset, true, true);
				int count = RagdollMapper.countModelParts(entity, model);
				
				if (count < ragdoll.bodies.size()) {
					// offsets for invisible head and hat
					boolean hasHat = ragdoll.bodies.size() % count != 0;
					int overlays = (int) Math.ceil(ragdoll.bodies.size() / (double) count);
					int offset = 0;
					
					for (int i = 1; i < overlays; i++) {
						offset += count;
						
						if (i == 1 && hasHat) {
							offset -= 4;
						} else {
							ragdoll.addConnection(headOffset + offset, headOffset, true, true);
							ragdoll.addConnection(headOffset + 1 + offset, headOffset, true, true);
							ragdoll.addConnection(hatOffset + offset, headOffset, true, true);
							ragdoll.addConnection(hatOffset + 1 + offset, headOffset, true, true);
						}
						
						ragdoll.addConnection(bodyOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(bodyOffset + 1 + offset, bodyOffset, true, true);
						ragdoll.addConnection(leftArmOffset + offset, leftArmOffset, true, true);
						ragdoll.addConnection(rightArmOffset + offset, rightArmOffset, true, true);
						ragdoll.addConnection(rightLegOffset + offset, rightLegOffset, true, true);
						ragdoll.addConnection(leftLegOffset + offset, leftLegOffset, true, true);
					}
				}
			}
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof QuadrupedModel animal) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			int headOffset = indices.get("head").index;
			
			if (model instanceof GoatModel) {
				GoatRenderState goatState = (GoatRenderState) renderState;
				headOffset = headOffset + 2;
				ragdoll.addConnection(headOffset - 2, headOffset, true, true); // right ear
				ragdoll.addConnection(headOffset - 1, headOffset, true, true); // left ear
				
				if (goatState.hasLeftHorn) ragdoll.addConnection(indices.get("left_horn").index, headOffset, true);
				if (goatState.hasRightHorn) ragdoll.addConnection(indices.get("right_horn").index, headOffset, true);
				
				ragdoll.addConnection(indices.get("nose").index, headOffset, true, true);
			}
			
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_front_leg").index;
			int leftArmOffset = indices.get("left_front_leg").index;
			int rightLegOffset = indices.get("right_hind_leg").index;
			int leftLegOffset = indices.get("left_hind_leg").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof ChickenModel) { 
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int beakOffset = indices.get("beak").index;
			int wattleOffset = indices.get("red_thing").index;
			int bodyOffset = indices.get("body").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			int rightWingOffset = indices.get("right_wing").index;
			int leftWingOffset = indices.get("left_wing").index;
			
			ragdoll.addConnection(beakOffset, headOffset, true);
			ragdoll.addConnection(wattleOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof WolfModel) { 
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			int rightHindLegOffset = indices.get("right_hind_leg").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int neckOffset = indices.get("upper_body").index;
			int tailOffset = indices.get("tail").index;
			
			ragdoll.addConnection(headOffset, neckOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(neckOffset, bodyOffset);

			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof SquidModel) {
			ragdoll.addConnection(0, 4);
			ragdoll.addConnection(1, 4);
			ragdoll.addConnection(2, 4);
			ragdoll.addConnection(3, 4);
			ragdoll.addConnection(5, 4);
			ragdoll.addConnection(6, 4);
			ragdoll.addConnection(7, 4);
			ragdoll.addConnection(8, 4);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof CreeperModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_front_leg").index;
			int leftArmOffset = indices.get("left_front_leg").index;
			int rightLegOffset = indices.get("right_hind_leg").index;
			int leftLegOffset = indices.get("left_hind_leg").index;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof DolphinModel) {
			int bodyOffset = 0;
			int headOffset = 1;
			int noseOffset = 2;
			int leftFinOffset = 3;
			int rightFinOffset = 4;
			int tailOffset = 5;
			int tailFinOffset = 6;
			int backFinOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true);
			ragdoll.addConnection(backFinOffset, bodyOffset, true);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(tailFinOffset, tailOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof GhastModel) {
			ragdoll.addConnection(0, 5);
			ragdoll.addConnection(1, 5);
			ragdoll.addConnection(2, 5);
			ragdoll.addConnection(3, 5);
			ragdoll.addConnection(4, 5);
			ragdoll.addConnection(6, 5);
			ragdoll.addConnection(7, 5);
			ragdoll.addConnection(8, 5);
			ragdoll.addConnection(9, 5);
		} else if (model instanceof HappyGhastModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int bodyOffset = indices.get("body").index;
			
			ragdoll.addConnection(indices.get("tentacle0").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle1").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle2").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle3").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle4").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle5").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle6").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle7").index, bodyOffset);
			ragdoll.addConnection(indices.get("tentacle8").index, bodyOffset);
			
			if (indices.get("inner_body") != null) {
				int innerBodyOffset = indices.get("inner_body").index;
				
				ragdoll.addConnection(innerBodyOffset, bodyOffset, true, true);
			}
		} else if (model instanceof IronGolemModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int rightArmOffset = 2;
			int leftLegOffset = 3;
			int leftArmOffset = 4;
			int rightLegOffset = 5;
			int bodyOffset = 6;
			int lowerBodyOffset = 7;
			
			ragdoll.addConnection(headOffset, lowerBodyOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(leftArmOffset, lowerBodyOffset);
			ragdoll.addConnection(rightArmOffset, lowerBodyOffset);
			ragdoll.addConnection(leftLegOffset, lowerBodyOffset);
			ragdoll.addConnection(rightLegOffset, lowerBodyOffset);
			ragdoll.addConnection(bodyOffset, lowerBodyOffset, true);
		} else if (model instanceof SpiderModel) {
			int headOffset = 0;
			int rightFrontLegOffset = 1;
			int rightHindLegOffset = 2;
			int leftMiddleFrontLegOffset = 3;
			int body0Offset = 4;
			int body1Offset = 5;
			int leftHindLegOffset = 6;
			int rightMiddleHindLegOffset = 7;
			int rightMiddleFrontLegOffset = 8;
			int leftMiddleHindLegOffset = 9;
			int leftFrontLegOffset = 10;
			
			ragdoll.addConnection(headOffset, body0Offset);
			ragdoll.addConnection(body1Offset, body0Offset);
			ragdoll.addConnection(rightFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftMiddleFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightMiddleHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightMiddleFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftMiddleHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftFrontLegOffset, body0Offset).stopCollision = true;
		} else if (model instanceof SnowGolemModel) {
			int headOffset = 0;
			int rightArmOffset = 1;
			int upperBodyOffset = 2;
			int leftArmOffset = 3;
			int lowerBodyOffset = 4;
			int pumpkinOffset = 5;

			ragdoll.addConnection(headOffset, upperBodyOffset);
			ragdoll.addConnection(rightArmOffset, upperBodyOffset);
			ragdoll.addConnection(leftArmOffset, upperBodyOffset);
			ragdoll.addConnection(upperBodyOffset, lowerBodyOffset);
			
			if (ragdoll.bodies.size() == 6) ragdoll.addConnection(pumpkinOffset, headOffset, true);
		} else if (model instanceof GuardianModel) {
			int headOffset = 0;
			int spike0 = 21;
			int spike1 = 5;
			int spike2 = 6;
			int spike3 = 7;
			int spike4 = 8;
			int spike5 = 9;
			int spike6 = 10;
			int spike7 = 16;
			int spike8 = 17;
			int spike9 = 18;
			int spike10 = 19;
			int spike11 = 20;
			int eye = 11;
			int tail0 = 12;
			int tail1 = 13;
			int tail2 = 14;
			int tail3 = 15;

			// stretches the tail too much
//			ragdoll.addConnection(tail0, headOffset);
//			ragdoll.addConnection(tail1, tail0);
//			ragdoll.addConnection(tail2, tail1);
//			ragdoll.addConnection(tail3, tail2);
			ragdoll.addConnection(tail0, headOffset, true);
			ragdoll.addConnection(tail1, headOffset, true);
			ragdoll.addConnection(tail2, headOffset, true);
			ragdoll.addConnection(tail3, headOffset, true);
			
			ragdoll.addConnection(spike0, headOffset, true);
			ragdoll.addConnection(spike1, headOffset, true);
			ragdoll.addConnection(spike2, headOffset, true);
			ragdoll.addConnection(spike3, headOffset, true);
			ragdoll.addConnection(spike4, headOffset, true);
			ragdoll.addConnection(spike5, headOffset, true);
			ragdoll.addConnection(spike6, headOffset, true);
			ragdoll.addConnection(spike7, headOffset, true);
			ragdoll.addConnection(spike8, headOffset, true);
			ragdoll.addConnection(spike9, headOffset, true);
			ragdoll.addConnection(spike10, headOffset, true);
			ragdoll.addConnection(spike11, headOffset, true);
			ragdoll.addConnection(headOffset + 1, headOffset, true);
			ragdoll.addConnection(headOffset + 2, headOffset, true);
			ragdoll.addConnection(headOffset + 3, headOffset, true);
			ragdoll.addConnection(headOffset + 4, headOffset, true);
			ragdoll.addConnection(eye, headOffset, true);
		} else if (model instanceof WitchModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int bodyOffset = indices.get("body").index;
			int hatOffset = indices.get("hat").index;
			int hat2Offset = indices.get("hat2").index;
			int hat3Offset = indices.get("hat3").index;
			int hat4Offset = indices.get("hat4").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			int armsOffset = indices.get("arms").index;
			int jacketOffset = indices.get("jacket").index;
			int moleOffset = indices.get("mole").index;

			ragdoll.addConnection(moleOffset, headOffset, true);
			ragdoll.addConnection(hat2Offset, headOffset, true);
			ragdoll.addConnection(hat3Offset, headOffset, true);
			ragdoll.addConnection(hat4Offset, headOffset, true);
			ragdoll.addConnection(hatOffset, headOffset, true);
//			ragdoll.addConnection(hatRimOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(jacketOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 2, bodyOffset, true);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(armsOffset, bodyOffset);
		} else if (model instanceof VillagerModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int bodyOffset = indices.get("body").index;
			int hatOffset = indices.get("hat").index;
			int hatRimOffset = indices.get("hat_rim").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			int armsOffset = indices.get("arms").index;
			int jacketOffset = indices.get("jacket").index;

			ragdoll.addConnection(hatOffset, headOffset, true);
			ragdoll.addConnection(hatRimOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(jacketOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 2, bodyOffset, true);
			ragdoll.addConnection(rightLegOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(leftLegOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(armsOffset, bodyOffset);

			int count = RagdollMapper.countModelParts(entity, model);
			
			if (count < ragdoll.bodies.size()) {
				// offsets for invisible head and hat
				boolean hasHat = ragdoll.bodies.size() % count != 0;
				int overlays = (int) Math.ceil(ragdoll.bodies.size() / (double) count);
				int offset = 0;
				
				for (int i = 1; i < overlays; i++) {
					offset += count;
					
					if (i == 1 && hasHat) {
						offset -= 4;
					} else {
						ragdoll.addConnection(hatOffset + offset, headOffset, true, true);
						ragdoll.addConnection(hatRimOffset + offset, headOffset, true, true);
						ragdoll.addConnection(noseOffset + offset, headOffset, true, true);
						ragdoll.addConnection(headOffset + offset, headOffset, true, true);
					}
					
					ragdoll.addConnection(bodyOffset + offset, bodyOffset, true, true);
					ragdoll.addConnection(jacketOffset + offset, bodyOffset, true, true);
					ragdoll.addConnection(armsOffset + offset, bodyOffset, true, true);
					ragdoll.addConnection(armsOffset + 1 + offset, bodyOffset, true, true);
					ragdoll.addConnection(armsOffset + 2 + offset, bodyOffset, true, true);
					ragdoll.addConnection(rightLegOffset + offset, rightLegOffset, true, true);
					ragdoll.addConnection(leftLegOffset + offset, leftLegOffset, true, true);
				}
			}
		} else if (model instanceof IllagerModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int bodyOffset = indices.get("body").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;

			IllagerRenderState illagerState = (IllagerRenderState) renderState;
			
			if (illagerState.armPose == AbstractIllager.IllagerArmPose.CROSSED) {
				// combined arms
				int armsOffset = indices.get("arms").index;
				int leftShoulderOffset = indices.get("left_shoulder").index;
				ragdoll.addConnection(noseOffset, headOffset, true);
				ragdoll.addConnection(headOffset, bodyOffset);
				ragdoll.addConnection(leftLegOffset, bodyOffset);
				ragdoll.addConnection(armsOffset, bodyOffset, true);
				ragdoll.addConnection(rightLegOffset, bodyOffset);
				ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
				ragdoll.addConnection(leftShoulderOffset, bodyOffset, true);
				ragdoll.addConnection(bodyOffset + 1, bodyOffset, true);
			} else {
				// individual arms
				int rightArmOffset = indices.get("right_arm").index;
				int leftArmOffset = indices.get("left_arm").index;
				ragdoll.addConnection(noseOffset, headOffset, true);
				ragdoll.addConnection(headOffset, bodyOffset);
				ragdoll.addConnection(leftLegOffset, bodyOffset);
				ragdoll.addConnection(rightArmOffset, bodyOffset);
				ragdoll.addConnection(rightLegOffset, bodyOffset);
				ragdoll.addConnection(leftArmOffset, bodyOffset);
				ragdoll.addConnection(bodyOffset + 1, bodyOffset, true);
			}
			
			if (RagdollMapper.countModelParts(entity, model) < ragdoll.bodies.size())
				ragdoll.addOverlayConnections(true);
		} else if (model instanceof StriderModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			int bodyOffset = indices.get("body").index;

			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(indices.get("right_top_bristle").index, bodyOffset, true);
			ragdoll.addConnection(indices.get("right_bottom_bristle").index, bodyOffset, true);
			ragdoll.addConnection(indices.get("left_top_bristle").index, bodyOffset, true);
			ragdoll.addConnection(indices.get("left_bottom_bristle").index, bodyOffset, true);
			ragdoll.addConnection(indices.get("right_middle_bristle").index, bodyOffset, true);
			ragdoll.addConnection(indices.get("left_middle_bristle").index, bodyOffset, true);

			ragdoll.bodies.get(bodyOffset).backfaceCulling = false;
		} else if (model instanceof RavagerModel) {
			int rightFrontLegOffset = 0;
			int rightHindLegOffset = 1;
			int leftHindLegOffset = 2;
			int neckOffset = 3;
			int headOffset = 4;
			int headChildOffset = 5;
			int rightHornOffset = 6;
			int mouthOffset = 7;
			int leftHornOffset = 8;
			int bodyOffset = 9;
			int bodyChildOffset = 10;
			int leftFrontLegOffset = 11;

			ragdoll.addConnection(bodyChildOffset, bodyOffset, true);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(neckOffset, headOffset, true);
			ragdoll.addConnection(mouthOffset, headOffset, true);
			ragdoll.addConnection(rightHornOffset, headOffset, true);
			ragdoll.addConnection(leftHornOffset, headOffset, true);
			ragdoll.addConnection(headChildOffset, headOffset, true);
		} else if (model instanceof BatModel) {
			int headOffset = 0;
			int rightEarOffset = 1;
			int leftEarOffset = 2;
			int bodyOffset = 3;
			int bodyChildOffset = 4;
			int rightWingOffset = 5;
			int rightWingTipOffset = 6;
			int leftWingOffset = 7;
			int leftWingTipOffset = 8;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(bodyChildOffset, bodyOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);

			ragdoll.addConnection(rightWingTipOffset, rightWingOffset, true);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingTipOffset, leftWingOffset, true);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
		} else if (model instanceof BeeModel) {
			int frontLegsOffset = 0;
			int rightWingOffset = 1;
			int leftWingOffset = 2;
			int middleLegsOffset = 3;
			int leftAntennaOffset = 4;
			int rightAntennaOffset = 5;
			int stingerOffset = 6;
			int bodyOffset = 7;
			int backLegsOffset = 8;

			ragdoll.addConnection(frontLegsOffset, bodyOffset, true);
			ragdoll.addConnection(rightWingOffset, bodyOffset, true);
			ragdoll.addConnection(leftWingOffset, bodyOffset, true);
			ragdoll.addConnection(middleLegsOffset, bodyOffset, true);
			ragdoll.addConnection(rightAntennaOffset, bodyOffset, true);
			ragdoll.addConnection(stingerOffset, bodyOffset, true);
			ragdoll.addConnection(leftAntennaOffset, bodyOffset, true);
			ragdoll.addConnection(backLegsOffset, bodyOffset, true);
		} else if (model instanceof RabbitModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int rightHindFootOffset = indices.get("right_hind_foot").index;
			int tailOffset = indices.get("tail").index;
			int leftHaunchOffset = indices.get("left_haunch").index;
			int rightHaunchOffset = indices.get("right_haunch").index;
			int bodyOffset = indices.get("body").index;
			int rightEarOffset = indices.get("right_ear").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			int leftHindFootOffset = indices.get("left_hind_foot").index;
			int leftEarOffset = indices.get("left_ear").index;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset, true);
			ragdoll.addConnection(rightHaunchOffset, bodyOffset);
			ragdoll.addConnection(leftHaunchOffset, bodyOffset);
			ragdoll.addConnection(rightHindFootOffset, rightHaunchOffset, true);
			ragdoll.addConnection(leftHindFootOffset, leftHaunchOffset, true);
		} else if (model instanceof WitherBossModel) {
			int shouldersOffset = 0;
			int ribcageOffset = 1;
			int tailOffset = 5;
			int leftHeadOffset = 6;
			int rightHeadOffset = 7;
			int centerHeadOffset = 8;

			ragdoll.addConnection(tailOffset, ribcageOffset);
			ragdoll.addConnection(shouldersOffset, ribcageOffset, true);
			ragdoll.addConnection(leftHeadOffset, ribcageOffset);
			ragdoll.addConnection(rightHeadOffset, ribcageOffset);
			ragdoll.addConnection(centerHeadOffset, ribcageOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof FelineModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_front_leg").index;
			int leftArmOffset = indices.get("left_front_leg").index;
			int rightLegOffset = indices.get("right_hind_leg").index;
			int leftLegOffset = indices.get("left_hind_leg").index;
			int upperTailOffset = indices.get("tail1").index;
			int lowerTailOffset = indices.get("tail2").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(upperTailOffset, bodyOffset);
			ragdoll.addConnection(lowerTailOffset, upperTailOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof FoxModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int rightEarOffset = indices.get("right_ear").index;
			int leftEarOffset = indices.get("left_ear").index;
			int bodyOffset = indices.get("body").index;
			int tailOffset = indices.get("tail").index;
			int leg1Offset = indices.get("right_hind_leg").index;
			int leg2Offset = indices.get("left_hind_leg").index;
			int leg3Offset = indices.get("right_front_leg").index;
			int leg4Offset = indices.get("left_front_leg").index;

			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(leg1Offset, bodyOffset);
			ragdoll.addConnection(leg2Offset, bodyOffset);
			ragdoll.addConnection(leg3Offset, bodyOffset);
			ragdoll.addConnection(leg4Offset, bodyOffset);
		} else if (model instanceof SilverfishModel) {
			int segment2Offset = 0;
			int segment1Offset = 1;
			int segment0Offset = 2;
			// big one
			int layer0Offset = 3;
			// small one
			int layer1Offset = 4;
			// medium one
			int layer2Offset = 5;
			int segment6Offset = 6;
			int segment5Offset = 7;
			int segment4Offset = 8;
			int segment3Offset = 9;

			ragdoll.addConnection(segment0Offset, segment1Offset);
			ragdoll.addConnection(segment1Offset, segment2Offset);
			ragdoll.addConnection(segment2Offset, segment3Offset);
			ragdoll.addConnection(segment3Offset, segment4Offset);
			ragdoll.addConnection(segment4Offset, segment5Offset);
			ragdoll.addConnection(segment5Offset, segment6Offset);

			ragdoll.addConnection(layer0Offset, segment2Offset, true);
			ragdoll.addConnection(layer1Offset, segment4Offset, true);
			ragdoll.addConnection(layer2Offset, segment1Offset, true);
		} else if (model instanceof EndermiteModel) {
			int segment2Offset = 0;
			int segment1Offset = 1;
			int segment0Offset = 2;
			int segment3Offset = 3;

			ragdoll.addConnection(segment0Offset, segment1Offset);
			ragdoll.addConnection(segment1Offset, segment2Offset);
			ragdoll.addConnection(segment2Offset, segment3Offset);
		} else if (model instanceof ParrotModel) {
			int headOffset = 0;
			int beak1Offset = 1;
			int beak2Offset = 2;
			int featherOffset = 3;
			int head2Offset = 4;
			int leftLegOffset = 5;
			int rightWingOffset = 6;
			int rightLegOffset = 7;
			int tailOffset = 8;
			int leftWingOffset = 9;
			int bodyOffset = 10;

			ragdoll.addConnection(beak1Offset, headOffset, true);
			ragdoll.addConnection(beak2Offset, headOffset, true);
			ragdoll.addConnection(featherOffset, headOffset, true);
			ragdoll.addConnection(head2Offset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
		} else if (model instanceof AbstractEquineModel horse) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			EquineRenderState equineState = (EquineRenderState) renderState;
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
			
			boolean hasSaddle = equineState.saddle != ItemStack.EMPTY;
			int neckOffset = indices.get("head_parts").index;
			int bodyOffset = indices.get("body").index;
			int rightHindLegOffset = indices.get("right_hind_leg").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			int leftEarOffset = indices.get("left_ear").index;
			int rightEarOffset = indices.get("right_ear").index;
			int headOffset = indices.get("head").index;
			
			int mane = indices.get("mane").index;
			int upperMouth = indices.get("upper_mouth").index;
			int tail = indices.get("tail").index;
			
			if (hasSaddle) {
				int leftSaddleMouthOffset = indices.get("left_saddle_mouth").index;
				int mouthSaddleWrapOffset = indices.get("mouth_saddle_wrap").index;
				// right saddle line and left saddle line are never used
				int rightSaddleLineOffset = indices.get("right_saddle_line").index;
				int rightSaddleMouthOffset = indices.get("right_saddle_mouth").index;
				int leftSaddleLineOffset = indices.get("left_saddle_line").index;
				int saddleOffset = indices.get("saddle").index;
				int headSaddleOffset = indices.get("head_saddle").index;

				ragdoll.addConnection(leftSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(mouthSaddleWrapOffset, neckOffset, true);
				ragdoll.addConnection(rightSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(headSaddleOffset, neckOffset, true);
				ragdoll.addConnection(saddleOffset, bodyOffset, true);
			}
			
			ragdoll.addConnection(headOffset, neckOffset, true);
			ragdoll.addConnection(leftEarOffset, neckOffset, true);
			ragdoll.addConnection(rightEarOffset, neckOffset, true);
			
			ragdoll.addConnection(tail, bodyOffset);
			ragdoll.addConnection(mane, neckOffset, true);
			ragdoll.addConnection(upperMouth, neckOffset, true);
			
			int count = RagdollMapper.countModelParts(entity, model);
			
			if (count < ragdoll.bodies.size())
				ragdoll.addOverlayConnections(true, Math.max(2, ragdoll.bodies.size() / count));
		} else if (model instanceof LlamaModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			int headOffset = indices.get("head").index;
			int neckOffset = headOffset + 1;
			int earLeftOffset = headOffset + 2;
			int earRightOffset = headOffset + 3;
			int bodyOffset = indices.get("body").index;
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int rightHindLegOffset = indices.get("right_hind_leg").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			
			// don't use chest positions to let them pop off the model
			int rightChestOffset = indices.get("right_chest").index;
			int leftChestOffset = indices.get("left_chest").index;
			
			ragdoll.addConnection(headOffset, neckOffset, true);
			ragdoll.addConnection(earLeftOffset, neckOffset, true);
			ragdoll.addConnection(earRightOffset, neckOffset, true);
			
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			if (RagdollMapper.countModelParts(entity, model) < ragdoll.bodies.size())
				ragdoll.addOverlayConnections(true);
		} else if (model instanceof CamelModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			CamelRenderState camelRenderState = (CamelRenderState) renderState;
			Equippable equippable = camelRenderState.saddle.get(DataComponents.EQUIPPABLE);
	        boolean saddle = equippable != null && !equippable.assetId().isEmpty();
	        boolean reins = camelRenderState.isRidden;
	        
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			int rightHindLegOffset = indices.get("right_hind_leg").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int bodyOffset = indices.get("body").index;
			int headOffset = indices.get("head").index;
			int neck1Offset = headOffset + 1;
			int neck2Offset = headOffset + 2;
			int earRightOffset = indices.get("right_ear").index;
			int earLeftOffset = indices.get("left_ear").index;
			int humpOffset = indices.get("hump").index;
			int tailOffset = indices.get("tail").index;
			
			if (saddle) {
				// we don't attach the reins in the end
				// because when the camel is dead it should no longer
				// have reins attached to it
				int reinOffset = reins ? 3 : 0;
				int saddleHump1Offset = 12 + 15 + reinOffset;
				int saddleHump2Offset = 12 + 16 + reinOffset;
				int saddleBodyOffset = 12 + 17 + reinOffset;
				int bridleOffset = 12 + 7;
				
				ragdoll.addConnection(bridleOffset, neck2Offset, true);
				ragdoll.addConnection(bridleOffset + 1, neck2Offset, true);
				ragdoll.addConnection(bridleOffset + 2, neck2Offset, true);
				ragdoll.addConnection(bridleOffset + 3, neck2Offset, true);
				ragdoll.addConnection(bridleOffset + 4, neck2Offset, true);

				ragdoll.addConnection(saddleHump1Offset, bodyOffset, true);
				ragdoll.addConnection(saddleHump2Offset, bodyOffset, true);
				ragdoll.addConnection(saddleBodyOffset, bodyOffset, true);
			}
			
			ragdoll.addConnection(headOffset, neck2Offset, true);
			ragdoll.addConnection(earLeftOffset, neck2Offset, true);
			ragdoll.addConnection(earRightOffset, neck2Offset, true);
			ragdoll.addConnection(neck1Offset, neck2Offset, true);
			
			ragdoll.addConnection(humpOffset, bodyOffset, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true);
			ragdoll.addConnection(neck2Offset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			ragdoll.removeUnused();
		} else if (model instanceof HoglinModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_front_leg").index;
			int leftArmOffset = indices.get("left_front_leg").index;
			int rightLegOffset = indices.get("right_hind_leg").index;
			int leftLegOffset = indices.get("left_hind_leg").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			int rightHornOffset = indices.get("right_horn").index;
			int leftHornOffset = indices.get("left_horn").index;
			int rightEarOffset = indices.get("right_ear").index;
			int leftEarOffset = indices.get("left_ear").index;
			int maneOffset = indices.get("mane").index;
			
			ragdoll.addConnection(rightHornOffset, headOffset, true);
			ragdoll.addConnection(leftHornOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset);
			ragdoll.addConnection(leftEarOffset, headOffset);
			ragdoll.addConnection(maneOffset, bodyOffset, true);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof SalmonModel) {
			int headOffset = 0;
			int leftFinOffset = 1;
			int bodyBackOffset = 2;
			int topBackFinOffset = 3;
			int backFinOffset = 4;
			int rightFinOffset = 5;
			int bodyFrontOffset = 6;
			int topFrontFinOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyFrontOffset);
			ragdoll.addConnection(bodyBackOffset, bodyFrontOffset);
			
			ragdoll.addConnection(topFrontFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(topBackFinOffset, bodyBackOffset, true);
			ragdoll.addConnection(backFinOffset, bodyBackOffset, true);
		} else if (model instanceof AxolotlModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int bodyOffset = indices.get("body").index;
			int bodyGillsOffset = bodyOffset + 1;
			int headOffset = indices.get("head").index;
			int topGillsOffset = indices.get("top_gills").index;
			int leftGillsOffset = indices.get("left_gills").index;
			int rightGillsOffset = indices.get("right_gills").index;
			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int rightHindLegffset = indices.get("right_hind_leg").index;
			int tailOffset = indices.get("tail").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(bodyGillsOffset, bodyOffset, true);
			ragdoll.addConnection(topGillsOffset, headOffset, true);
			ragdoll.addConnection(leftGillsOffset, headOffset, true);
			ragdoll.addConnection(rightGillsOffset, headOffset, true);
			
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset, true);
			ragdoll.addConnection(rightHindLegffset, bodyOffset, true);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset, true);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true);
		} else if (model instanceof PhantomModel) {
			int bodyOffset = 0;
			int headOffset = 1;
			int rightWingBaseOffset = 2;
			int rightWingTipOffset = 3;
			int tailBaseOffset = 4;
			int tailTipOffset = 5;
			int leftWingBaseOffset = 6;
			int leftWingTipOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightWingBaseOffset, bodyOffset);
			ragdoll.addConnection(tailBaseOffset, bodyOffset);
			ragdoll.addConnection(leftWingBaseOffset, bodyOffset);
			
			ragdoll.addConnection(rightWingTipOffset, rightWingBaseOffset, true);
			ragdoll.addConnection(tailTipOffset, tailBaseOffset, true);
			ragdoll.addConnection(leftWingTipOffset, leftWingBaseOffset, true);
		} else if (model instanceof CodModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int leftFinOffset = 2;
			int topFinOffset = 3;
			int rightFinOffset = 4;
			int bodyOffset = 5;
			int tailFinOffset = 6;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(tailFinOffset, bodyOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyOffset, true);
			ragdoll.addConnection(topFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true);
		} else if (model instanceof PufferfishSmallModel) {
			int rightEyeOffset = 0;
			int leftFinOffset = 1;
			int rightFinOffset = 2;
			int leftEyeOffset = 3;
			int bodyOffset = 4;
			int backFinOffset = 5;
			
			ragdoll.addConnection(rightEyeOffset, bodyOffset, true);
			ragdoll.addConnection(leftEyeOffset, bodyOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyOffset, true);
			ragdoll.addConnection(backFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true);
		} else if (model instanceof PufferfishMidModel) {
			int leftBlueFinOffset = 0;
			int topBackFinOffset = 1;
			int leftBackFinOffset = 2;
			int leftFrontFinOffset = 3;
			int bottomFrontFinOffset = 4;
			int rightFrontFinOffset = 5;
			int rightBackFinOffset = 6;
			int bodyOffset = 7;
			int topFrontFinOffset = 8;
			int bottomBackFinOffset = 9;
			int rightBlueFinOffset = 10;
			
			ragdoll.addConnection(leftBlueFinOffset, bodyOffset, true);
			ragdoll.addConnection(topBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(leftBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(leftFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(bottomFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(topFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(bottomBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightBlueFinOffset, bodyOffset, true);
		} else if (model instanceof PufferfishBigModel) {
			int leftBlueFinOffset = 0;
			int topBackFinOffset = 1;
			int leftBackFinOffset = 2;
			int leftFrontFinOffset = 3;
			int bottomFrontFinOffset = 4;
			int bodyOffset = 5;
			int rightFrontFinOffset = 6;
			int rightBackFinOffset = 7;
			int topFrontFinOffset = 8;
			int bottomBackFinOffset = 9;
			int rightBlueFinOffset = 10;
			int rightBlueBackFinOffset = 11;
			int topBlueFrontFinOffset = 12;
			
			ragdoll.addConnection(leftBlueFinOffset, bodyOffset, true);
			ragdoll.addConnection(topBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(leftBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(leftFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(bottomFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(topFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(bottomBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightBlueBackFinOffset, bodyOffset, true);
			ragdoll.addConnection(topBlueFrontFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightBlueFinOffset, bodyOffset, true);
		} else if (model instanceof TropicalFishLargeModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int leftFinOffset = indices.get("left_fin").index;
			int topFinOffset = indices.get("top_fin").index;
			int tailOffset = indices.get("tail").index;
			int rightFinOffset = indices.get("right_fin").index;
			int bodyOffset = indices.get("body").index;
			int bottomFinOffset = indices.get("bottom_fin").index;
			
			ragdoll.addConnection(leftFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(topFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(bottomFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true, true);
			
			ragdoll.addOverlayConnections(true);
		} else if (model instanceof TropicalFishSmallModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			
			int leftFinOffset = indices.get("left_fin").index;
			int topFinOffset = indices.get("top_fin").index;
			int tailOffset = indices.get("tail").index;
			int rightFinOffset = indices.get("right_fin").index;
			int bodyOffset = indices.get("body").index;
			
			ragdoll.addConnection(leftFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(topFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true, true);

			ragdoll.addOverlayConnections(true);
		} else if (model instanceof TadpoleModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int bodyOffset = indices.get("body").index;
			int tailOffset = indices.get("tail").index;
			
			ragdoll.addConnection(tailOffset, bodyOffset, true, true);
		} else if (model instanceof FrogModel frog) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int leftLeg = indices.get("left_leg").index;
			int leftFoot = indices.get("left_foot").index;
			int rightLeg = indices.get("right_leg").index;
			int rightFoot = indices.get("right_foot").index;
			int body = indices.get("body").index;
			int bodyCroakingBody = indices.get("croaking_body").index;
			int head = indices.get("head").index;
			int head2 = head + 1;
			int rightEye = indices.get("right_eye").index;
			int leftEye = indices.get("left_eye").index;
			int rightArm = indices.get("right_arm").index;
			int rightHand = indices.get("right_hand").index;
			int tongue = indices.get("tongue").index;
			int leftArm = indices.get("left_arm").index;
			int leftHand = indices.get("left_hand").index;
			
			ragdoll.addConnection(leftFoot, leftLeg, true, true);
			ragdoll.addConnection(rightFoot, rightLeg, true, true);
			ragdoll.addConnection(leftLeg, body);
			ragdoll.addConnection(rightLeg, body);
			ragdoll.addConnection(head2, body, true);
			ragdoll.addConnection(rightEye, body, true);
			ragdoll.addConnection(leftEye, body, true);
			ragdoll.addConnection(rightArm, body);
			ragdoll.addConnection(leftArm, body);
			ragdoll.addConnection(rightHand, rightArm, true, true);
			ragdoll.addConnection(leftHand, leftArm, true, true);
			ragdoll.addConnection(tongue, body, true, true);
			ragdoll.addConnection(head, body, true, true);
			
			FrogRenderState frogState = (FrogRenderState) renderState;
			
			if (frogState.croakAnimationState.isStarted()) {
				ragdoll.addConnection(bodyCroakingBody, body, true);
			}
			
			RagdollMapper.getCuboids(ragdoll, frog.root(), new Counter());
		} else if (model instanceof AllayModel allay) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int head = indices.get("head").index;
			int body = indices.get("body").index;
			int body2 = body + 1;
			int rightArm = indices.get("right_arm").index;
			int leftArm = indices.get("left_arm").index;
			int rightWing = indices.get("right_wing").index;
			int leftWing = indices.get("left_wing").index;
			
			ragdoll.addConnection(head, body);
			ragdoll.addConnection(rightArm, body);
			ragdoll.addConnection(leftArm, body);
			ragdoll.addConnection(body2, body, true, true);
			ragdoll.addConnection(rightWing, body, true, true);
			ragdoll.addConnection(leftWing, body, true, true);
			
			RagdollMapper.getCuboids(ragdoll, allay.root(), new Counter());
		} else if (model instanceof WardenModel warden) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int bone = 0;
			int rightLeg = indices.get("right_leg").index;
			int leftLeg = indices.get("left_leg").index;
			int body = indices.get("body").index;
			int head = indices.get("head").index;
			int rightTendril = indices.get("right_tendril").index;
			int leftTendril = indices.get("left_tendril").index;
			int rightArm = indices.get("right_arm").index;
			int leftArm = indices.get("left_arm").index;
			int rightRibcage = indices.get("right_ribcage").index;
			int leftRibcage = indices.get("left_ribcage").index;
			
			ragdoll.addConnection(rightTendril, head, true, true);
			ragdoll.addConnection(leftTendril, head, true, true);
			ragdoll.addConnection(head, body);
			ragdoll.addConnection(rightArm, body);
			ragdoll.addConnection(leftArm, body);
			ragdoll.addConnection(rightLeg, body);
			ragdoll.addConnection(leftLeg, body);
			ragdoll.addConnection(leftRibcage, body, true, true);
			ragdoll.addConnection(rightRibcage, body, true, true);

			ragdoll.removeUnused();
			
			// emissive layers don't make sense when warden is dead
//			int leftLegBL = 10;
//			int rightLegBL = 11;
//			int headBL = 12;
//			int rightArmBL = 13;
//			int leftArmBL = 14;
//
//			int leftLegP1 = 15;
//			int rightLegP1 = 16;
//			int bodyP1 = 17;
//			int headP1 = 18;
//			int rightArmP1 = 19;
//			int leftArmP1 = 20;
//
//			int leftLegP2 = 21;
//			int rightLegP2 = 22;
//			int bodyP2 = 23;
//			int headP2 = 24;
//			int rightArmP2 = 25;
//			int leftArmP2 = 26;
//
//			int rightTendrilT = 27;
//			int leftTendrilT = 28;
//			
//			int bodyH = 29;
//
//			ragdoll.addConnection(headBL, head, true, true);
//			ragdoll.addConnection(leftArmBL, leftArm, true, true);
//			ragdoll.addConnection(rightArmBL, rightArm, true, true);
//			ragdoll.addConnection(leftLegBL, leftLeg, true, true);
//			ragdoll.addConnection(rightLegBL, rightLeg, true, true);
//			
//			ragdoll.addConnection(bodyP1, body, true, true);
//			ragdoll.addConnection(headP1, head, true, true);
//			ragdoll.addConnection(leftArmP1, leftArm, true, true);
//			ragdoll.addConnection(rightArmP1, rightArm, true, true);
//			ragdoll.addConnection(leftLegP1, leftLeg, true, true);
//			ragdoll.addConnection(rightLegP1, rightLeg, true, true);
//			
//			ragdoll.addConnection(bodyP2, body, true, true);
//			ragdoll.addConnection(headP2, head, true, true);
//			ragdoll.addConnection(leftArmP2, leftArm, true, true);
//			ragdoll.addConnection(rightArmP2, rightArm, true, true);
//			ragdoll.addConnection(leftLegP2, leftLeg, true, true);
//			ragdoll.addConnection(rightLegP2, rightLeg, true, true);
//			
//			ragdoll.addConnection(leftTendrilT, leftTendril, true, true);
//			ragdoll.addConnection(rightTendrilT, rightTendril, true, true);
//			ragdoll.addConnection(bodyH, body, true, true);
		} else if (model instanceof EnderDragonModel) {
			// ender dragon model is null because ender dragon has no living entity renderer and thus
			// we have no access to it's model
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int head = indices.get("head").index;
			
			for (int i = 0; i < 4; i++) {
				// do neck
				ragdoll.addConnection(indices.get("neck" + Integer.toString(i + 1)).index, indices.get("neck" + Integer.toString(i)).index);
			}
			
			int jaw = indices.get("jaw").index;
			int body = indices.get("body").index;
			
			int leftWing = indices.get("left_wing").index;
			int leftWingTip = indices.get("left_wing_tip").index;
			int leftFrontLeg = indices.get("left_front_leg").index;
			int leftFrontLegTip = indices.get("left_front_leg_tip").index;
			int leftFrontFoot = indices.get("left_front_foot").index;
			int leftHindLeg = indices.get("left_hind_leg").index;
			int leftHindLegTip = indices.get("left_hind_leg_tip").index;
			int leftHindFoot = indices.get("left_hind_foot").index;
			
			int rightWing = indices.get("right_wing").index;
			int rightWingTip = indices.get("right_wing_tip").index;
			int rightFrontLeg = indices.get("right_front_leg").index;
			int rightFrontLegTip = indices.get("right_front_leg_tip").index;
			int rightFrontFoot = indices.get("right_front_foot").index;
			int rightHindLeg = indices.get("right_hind_leg").index;
			int rightHindLegTip = indices.get("right_hind_leg_tip").index;
			int rightHindFoot = indices.get("right_hind_foot").index;
			
			ragdoll.addConnection(jaw, head, true);
			ragdoll.addConnection(head, indices.get("neck4").index);
			
			ragdoll.addConnection(indices.get("neck0").index, body);
			
			ragdoll.addConnection(rightWing, body);
			ragdoll.addConnection(leftWing, body);
			
			ragdoll.addConnection(rightWingTip, rightWing);
			ragdoll.addConnection(leftWingTip, leftWing);
			
			ragdoll.addConnection(rightFrontLeg, body);
			ragdoll.addConnection(rightHindLeg, body);
			ragdoll.addConnection(leftFrontLeg, body);
			ragdoll.addConnection(leftHindLeg, body);
			
			ragdoll.addConnection(rightFrontLegTip, rightFrontLeg);
			ragdoll.addConnection(rightHindLegTip, rightHindLeg);
			ragdoll.addConnection(leftFrontLegTip, leftFrontLeg);
			ragdoll.addConnection(leftHindLegTip, leftHindLeg);
			
			ragdoll.addConnection(rightFrontFoot, rightFrontLegTip);
			ragdoll.addConnection(rightHindFoot, rightHindLegTip);
			ragdoll.addConnection(leftFrontFoot, leftFrontLegTip);
			ragdoll.addConnection(leftHindFoot, leftHindLegTip);
			
			for (int i = 0; i < 11; i++) {
				ragdoll.addConnection(indices.get("tail" + Integer.toString(i + 1)).index, indices.get("tail" + Integer.toString(i)).index);
			}
			
			ragdoll.addConnection(indices.get("tail0").index, body);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof SnifferModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);

			int rightFrontLegOffset = indices.get("right_front_leg").index;
			int rightHindLegOffset = indices.get("right_hind_leg").index;
			int rightMidLegOffset = indices.get("right_mid_leg").index;
			int leftHindLegOffset = indices.get("left_hind_leg").index;
			int bodyOffset = indices.get("body").index;
			int headOffset = indices.get("head").index;
			int noseOffset = indices.get("nose").index;
			int lowerBeakOffset = indices.get("lower_beak").index;
			int rightEarOffset = indices.get("right_ear").index;
			int leftEarOffset = indices.get("left_ear").index;
			int leftMidLegOffset = indices.get("left_mid_leg").index;
			int leftFrontLegOffset = indices.get("left_front_leg").index;

			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(lowerBeakOffset, headOffset, true);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightMidLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftMidLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		} else if (model instanceof ArmadilloModel) {
			ArmadilloRenderState armadilloState = (ArmadilloRenderState) renderState;
			
			boolean inShell = armadilloState.isHidingInShell;
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			if (inShell) {
				// body uses skip draw which causes it to not show up in the cuboids, so subtract everything
				// after the body by the amount of cuboids skipped by body (2 in this case)
				// first time skip draw variable gets used (except for warden emissive layers)
				int cubeOffset = indices.get("cube").index;
				int rightFrontLegOffset = 0;
				int headOffset = 2;
				int rightEarOffset = 3;
				int leftEarOffset = 4;
				int leftFrontLegOffset = 5;
	
				ragdoll.addConnection(rightEarOffset, cubeOffset, true, true);
				ragdoll.addConnection(leftEarOffset, cubeOffset, true, true);
				
				ragdoll.addConnection(headOffset, cubeOffset, true, true);
				
				ragdoll.addConnection(rightFrontLegOffset, cubeOffset);
				ragdoll.addConnection(leftFrontLegOffset, cubeOffset);
			} else {
				int rightFrontLegOffset = indices.get("right_front_leg").index;
				int leftFrontLegOffset = indices.get("left_front_leg").index;
				int headOffset = indices.get("head_cube").index;
				int rightEarOffset = indices.get("right_ear_cube").index;
				int leftEarOffset = indices.get("left_ear_cube").index;
				int rightHindLegOffset = indices.get("right_hind_leg").index;
				int leftHindLegOffset = indices.get("left_hind_leg").index;
				int bodyOffset = indices.get("body").index;
				int tailOffset = indices.get("tail").index;
	
				ragdoll.addConnection(rightEarOffset, headOffset, true);
				ragdoll.addConnection(leftEarOffset, headOffset, true);
				
				ragdoll.addConnection(headOffset, bodyOffset);
				
				ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
				ragdoll.addConnection(rightHindLegOffset, bodyOffset);
				ragdoll.addConnection(leftHindLegOffset, bodyOffset);
				ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
				ragdoll.addConnection(tailOffset, bodyOffset);
				RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
			}
		} else if (model instanceof CopperGolemModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int headOffset = indices.get("head").index;
			int bodyOffset = indices.get("body").index;
			int rightArmOffset = indices.get("right_arm").index;
			int leftArmOffset = indices.get("left_arm").index;
			int rightLegOffset = indices.get("right_leg").index;
			int leftLegOffset = indices.get("left_leg").index;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);

			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
			ragdoll.addOverlayConnections(true);
		} else if (model instanceof NautilusModel) {
			Map<String, ModelPartIndex> indices = RagdollMapper.getModelPartIndices(model);
			
			int shellOffset = indices.get("shell").index;
			int bodyOffset = indices.get("body").index;
			int lowerMouthOffset = indices.get("lower_mouth").index;
			int upperMouthOffset = indices.get("upper_mouth").index;
			int innerMouthOffset = indices.get("inner_mouth").index;

			ragdoll.addConnection(bodyOffset, shellOffset, true);
			ragdoll.addConnection(lowerMouthOffset, shellOffset, true, true);
			ragdoll.addConnection(upperMouthOffset, shellOffset, true, true);
			ragdoll.addConnection(innerMouthOffset, shellOffset, true, true);

			RagdollMapper.getCuboids(ragdoll, model.root(), new Counter());
		}
	}

	@Override
	public void filterCuboidsFromEntities(List<PhysicsEntity> blockifiedEntity, Entity entity, EntityModel model) {
		// when ragdolls are enabled you don't want to filter overlays
		// like the dots on horses
		boolean ragdollsEnabled = RagdollMapper.areRagdollsEnabled(entity);
		
		if (model instanceof IronGolemModel) {
			while (blockifiedEntity.size() > 8) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SpiderModel) {
			while (blockifiedEntity.size() > 11) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof StriderModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof WitherBossModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SheepModel) {
			while (blockifiedEntity.size() > 6) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (entity instanceof EnderDragon) {
			// 31 for all the model parts and the neck and tail get rendered additionally (17 * 2)
			while (blockifiedEntity.size() > 31 + 17 * 2) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof TropicalFishLargeModel || model instanceof TropicalFishSmallModel || model instanceof SkeletonModel || model instanceof HorseModel ||
				model instanceof LlamaModel || model instanceof DrownedModel || model instanceof IllagerModel || model instanceof VillagerModel || 
				model instanceof EndermanModel) {
			int count = RagdollMapper.countModelParts(entity, model);

			if (!ragdollsEnabled) {
				while (blockifiedEntity.size() > count) {
					blockifiedEntity.remove(blockifiedEntity.size() - 1);
				}
			}
		} else if (model instanceof PhantomModel || model instanceof ZombieModel || model instanceof PiglinModel) {
			int count = RagdollMapper.countModelParts(entity, model);
			
			while (blockifiedEntity.size() > count) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof NautilusModel) {
			Iterator<PhysicsEntity> it = blockifiedEntity.iterator();
			
			while (it.hasNext()) {
				PhysicsEntity physicsEntity = it.next();

				if (physicsEntity.feature instanceof SimpleEquipmentLayer) {
					it.remove();
				}
			}
		}
		
		// remove unnecessary features
		Iterator<PhysicsEntity> it = blockifiedEntity.iterator();
		
		while (it.hasNext()) {
			PhysicsEntity physicsEntity = it.next();

			if (physicsEntity.feature instanceof HumanoidArmorLayer || physicsEntity.feature instanceof CustomHeadLayer || 
					physicsEntity.feature instanceof WingsLayer || physicsEntity.feature instanceof ItemInHandLayer || 
					physicsEntity.feature instanceof ArrowLayer || physicsEntity.feature instanceof Deadmau5EarsLayer || 
					physicsEntity.feature instanceof CapeLayer || physicsEntity.feature instanceof SpinAttackEffectLayer || 
					physicsEntity.feature instanceof ParrotOnShoulderLayer || physicsEntity.feature instanceof BeeStingerLayer) {
//				if (!(model instanceof SkeletonModel && physicsEntity.feature instanceof ItemInHandLayer)) {
					it.remove();
//				}
			}
		}
	}

}
