package net.diebuddies.physics.ragdoll;

import java.util.Iterator;
import java.util.List;

import net.diebuddies.physics.PhysicsEntity;
import net.diebuddies.physics.ragdoll.RagdollMapper.Counter;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.render.entity.model.AxolotlEntityModel;
import net.minecraft.client.render.entity.model.BatEntityModel;
import net.minecraft.client.render.entity.model.BeeEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.CodEntityModel;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.DolphinEntityModel;
import net.minecraft.client.render.entity.model.DonkeyEntityModel;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.render.entity.model.EndermiteEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.FoxEntityModel;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.client.render.entity.model.GoatEntityModel;
import net.minecraft.client.render.entity.model.GuardianEntityModel;
import net.minecraft.client.render.entity.model.HoglinEntityModel;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.LargePufferfishEntityModel;
import net.minecraft.client.render.entity.model.LargeTropicalFishEntityModel;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.render.entity.model.MediumPufferfishEntityModel;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.client.render.entity.model.PiglinEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.client.render.entity.model.RavagerEntityModel;
import net.minecraft.client.render.entity.model.SalmonEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.render.entity.model.SmallPufferfishEntityModel;
import net.minecraft.client.render.entity.model.SmallTropicalFishEntityModel;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.render.entity.model.VexEntityModel;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.render.entity.model.WitchEntityModel;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.model.ZombieVillagerEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.IllusionerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.passive.AbstractDonkeyEntity;

public class VanillaRagdollHook implements RagdollHook {

	@Override
	public void map(RagdollPX ragdoll, Entity entity, EntityModel model) {
		if (model instanceof PiglinEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			PiglinEntityModel piglin = (PiglinEntityModel) model;
			// basic ragdoll for humans/zombies
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter, true);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);

			ragdoll.addConnection(4, headOffset);
			ragdoll.addConnection(5, headOffset);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(leftArmOffset, bodyOffset).stopCollision = true;
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);

			int hatOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			
			int leftPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int rightPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int leftSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int rightSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			int jacketOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);

			if (piglin.hat.visible) {
				ragdoll.addConnection(hatOffset, headOffset, true, true);
			}
			
			if (piglin.leftPants.visible) {
				ragdoll.addConnection(leftPantsOffset, leftLegOffset, true, true);
			}

			if (piglin.rightPants.visible) {
				ragdoll.addConnection(rightPantsOffset, rightLegOffset, true, true);
			}

			if (piglin.leftSleeve.visible) {
				ragdoll.addConnection(leftSleeveOffset, leftArmOffset, true, true);
			}

			if (piglin.rightSleeve.visible) {
				ragdoll.addConnection(rightSleeveOffset, rightArmOffset, true, true);
			}

			if (piglin.jacket.visible) {
				ragdoll.addConnection(jacketOffset, bodyOffset, true, true);
			}

			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter, true);
			}
		} else if (model instanceof VexEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			// basic ragdoll for humans/zombies
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int hatOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
		} else if (model instanceof BipedEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			// basic ragdoll for humans/zombies
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);

			int hatOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			if (((BipedEntityModel) model).hat.visible) {
				ragdoll.addConnection(hatOffset, headOffset, true, true);
			}
			
			if (model instanceof PlayerEntityModel) {
				PlayerEntityModel playerModel = (PlayerEntityModel) model;
				
				try {
					int leftPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int rightPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int leftSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int rightSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int jacketOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					
					if (playerModel.leftPants.visible) {
						ragdoll.addConnection(leftPantsOffset, leftLegOffset, true, true);
					}

					if (playerModel.rightPants.visible) {
						ragdoll.addConnection(rightPantsOffset, rightLegOffset, true, true);
					}

					if (playerModel.leftSleeve.visible) {
						ragdoll.addConnection(leftSleeveOffset, leftArmOffset, true, true);
					}

					if (playerModel.rightSleeve.visible) {
						ragdoll.addConnection(rightSleeveOffset, rightArmOffset, true, true);
					}

					if (playerModel.jacket.visible) {
						ragdoll.addConnection(jacketOffset, bodyOffset, true, true);
					}
				} catch (Exception e) {}
			} else if (model instanceof ArmorStandEntityModel) {
				try {
					int rightBodyStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int leftBodyStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int shoulderStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int basePlateOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					ragdoll.addConnection(rightBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(leftBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(shoulderStickOffset, bodyOffset, true);
				} catch (Exception e) {}
			} else if (model instanceof SkeletonEntityModel) {
				// this adds support for strays
				if (RagdollMapper.countModelParts(entity, model) < ragdoll.bodies.size())
					ragdoll.addOverlayConnections(true);
			} else if (model instanceof DrownedEntityModel) {
				int count = RagdollMapper.countModelParts(entity, model);

				if (ragdoll.bodies.size() > count * 2) {
					// with trident there gets something rendered before the overlay
					// so offset the indices by 1
					ragdoll.addOverlayConnections(true, 14, 5);
					
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
			} else if (model instanceof EndermanEntityModel) {
				ragdoll.addOverlayConnections(true);
			} else if (model instanceof ZombieVillagerEntityModel) {
				int count = RagdollMapper.countModelParts(entity, model);

				if (count < ragdoll.bodies.size()) {
					// offsets for invisible head and hat
					int nbodyOffset = 0;
					int nrightArmOffset = 2;
					int nleftArmOffset = 3;
					int nrightLegOffset = 4;
					int nleftLegOffset = 5;
					int overlays = (int) Math.ceil(ragdoll.bodies.size() / (double) count);

					boolean hasHat = ragdoll.bodies.size() % count != 0;
					
					for (int i = 1; i < overlays; i++) {
						int offset = count * i + ((i != 1) ? (hasHat ? -4 : 0) : 0);
						
						if (i == 1 && hasHat) {
							ragdoll.addConnection(nbodyOffset + offset, bodyOffset, true, true);
							ragdoll.addConnection(nbodyOffset + 1 + offset, bodyOffset, true, true);
							ragdoll.addConnection(nleftArmOffset + offset, leftArmOffset, true, true);
							ragdoll.addConnection(nrightArmOffset + offset, rightArmOffset, true, true);
							ragdoll.addConnection(nrightLegOffset + offset, rightLegOffset, true, true);
							ragdoll.addConnection(nleftLegOffset + offset, leftLegOffset, true, true);
						} else {
							ragdoll.addConnection(headOffset + offset, headOffset, true, true);
							// nose
							ragdoll.addConnection(headOffset + 1 + offset, headOffset, true, true);
							
							ragdoll.addConnection(bodyOffset + offset, bodyOffset, true, true);
							ragdoll.addConnection(bodyOffset + 1 + offset, bodyOffset, true, true);
							
							ragdoll.addConnection(leftArmOffset + offset, leftArmOffset, true, true);
							ragdoll.addConnection(rightArmOffset + offset, rightArmOffset, true, true);
							ragdoll.addConnection(rightLegOffset + offset, rightLegOffset, true, true);
							ragdoll.addConnection(leftLegOffset + offset, leftLegOffset, true, true);
							
							ragdoll.addConnection(hatOffset + offset, headOffset, true, true);
							// hat rim
							ragdoll.addConnection(hatOffset + 1 + offset, headOffset, true, true);
						}
					}
				}
			}
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof QuadrupedEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();

			int headOffset = 0;
			
			if (model instanceof GoatEntityModel) {
				counter.count = 6;
				headOffset = 3;
				ragdoll.addConnection(0, headOffset, true);
				ragdoll.addConnection(1, headOffset, true);
				ragdoll.addConnection(2, headOffset, true);
				ragdoll.addConnection(4, headOffset, true);
				ragdoll.addConnection(5, headOffset, true);
			} else {
				while (head.hasNext()) {
					RagdollMapper.getCuboids(ragdoll, head.next(), counter);
				}
			}
			
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof ChickenEntityModel) { 
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			int headOffset = 0;
			int beakOffset = RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			int wattleOffset = RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}

			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int bodyOffset = counter.count;
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(beakOffset, headOffset, true);
			ragdoll.addConnection(wattleOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof WolfEntityModel) { 
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			int headOffset = 0;
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}

			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int bodyOffset = counter.count;
			int rightFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int tailOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int neckOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, neckOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(neckOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof SquidEntityModel) {
			ragdoll.addConnection(0, 4);
			ragdoll.addConnection(1, 4);
			ragdoll.addConnection(2, 4);
			ragdoll.addConnection(3, 4);
			ragdoll.addConnection(5, 4);
			ragdoll.addConnection(6, 4);
			ragdoll.addConnection(7, 4);
			ragdoll.addConnection(8, 4);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof CreeperEntityModel) {
			int headOffset = 0;
			int rightArmOffset = 1;
			int rightLegOffset = 2;
			int leftLegOffset = 3;
			int bodyOffset = 4;
			int leftArmOffset = 5;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof DolphinEntityModel) {
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
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof GhastEntityModel) {
			ragdoll.addConnection(0, 5);
			ragdoll.addConnection(1, 5);
			ragdoll.addConnection(2, 5);
			ragdoll.addConnection(3, 5);
			ragdoll.addConnection(4, 5);
			ragdoll.addConnection(6, 5);
			ragdoll.addConnection(7, 5);
			ragdoll.addConnection(8, 5);
			ragdoll.addConnection(9, 5);
		} else if (model instanceof IronGolemEntityModel) {
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
			
//			ragdoll.addConnection(headOffset, bodyOffset);
//			ragdoll.addConnection(noseOffset, headOffset, true);
//			ragdoll.addConnection(leftArmOffset, bodyOffset);
//			ragdoll.addConnection(rightArmOffset, bodyOffset);
//			ragdoll.addConnection(leftLegOffset, lowerBodyOffset);
//			ragdoll.addConnection(rightLegOffset, lowerBodyOffset);
//			ragdoll.addConnection(lowerBodyOffset, bodyOffset);
		} else if (model instanceof SpiderEntityModel) {
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
		} else if (model instanceof SnowGolemEntityModel) {
			int headOffset = 0;
			int rightArmOffset = 1;
			int upperBodyOffset = 2;
			int leftArmOffset = 3;
			int lowerBodyOffset = 4;

			ragdoll.addConnection(headOffset, upperBodyOffset);
			ragdoll.addConnection(rightArmOffset, upperBodyOffset);
			ragdoll.addConnection(leftArmOffset, upperBodyOffset);
			ragdoll.addConnection(lowerBodyOffset, upperBodyOffset);
		} else if (model instanceof GuardianEntityModel) {
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
		} else if (model instanceof WitchEntityModel) {
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: head: 0
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: nose: 1
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: mole: 2
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: hat: 3
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: hat_rim: 4
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: hat2: 5
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: hat3: 6
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: hat4: 7
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: left_leg: 8
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: right_leg: 9
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: arms: 10
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: body: 13
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: jacket: 14
//			[12:14:20] [Render thread/INFO] (Minecraft) [STDOUT]: class net.minecraft.client.render.entity.model.WitchEntityModel
			int headOffset = 0;
			int noseOffset = 1;
			int moleOffset = 2;
			int hatOffset = 3;
			int hatRimOffset = 4;
			int hat2Offset = 5;
			int hat3Offset = 6;
			int hat4Offset = 7;
			int leftLegOffset = 8;
			int rightLegOffset = 9;
			int armsOffset = 10;
			int bodyOffset = 13;
			int jacketOffset = 14;

			ragdoll.addConnection(moleOffset, headOffset, true);
			ragdoll.addConnection(hat2Offset, headOffset, true);
			ragdoll.addConnection(hat3Offset, headOffset, true);
			ragdoll.addConnection(hat4Offset, headOffset, true);
			ragdoll.addConnection(hatOffset, headOffset, true);
			ragdoll.addConnection(hatRimOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(jacketOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 2, bodyOffset, true);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(armsOffset, bodyOffset);
		} else if (model instanceof VillagerResemblingModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int hatOffset = 2;
			int hatRimOffset = 3;
			int leftLegOffset = 4;
			int rightLegOffset = 5;
			int armsOffset = 6;
			int bodyOffset = 9;
			int jacketOffset = 10;

			ragdoll.addConnection(hatOffset, headOffset, true);
			ragdoll.addConnection(hatRimOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(jacketOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 2, bodyOffset, true);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(armsOffset, bodyOffset);
			
			int count = RagdollMapper.countModelParts(entity, model);
			
			if (count < ragdoll.bodies.size()) {
				// offsets for invisible head and hat
				int nleftLegOffset = 0;
				int nrightLegOffset = 1;
				int narmsOffset = 2;
				int nbodyOffset = 5;
				int njacketOffset = 6;
				int overlays = (int) Math.ceil(ragdoll.bodies.size() / (double) count);

				boolean hasHat = ragdoll.bodies.size() % count != 0;
				
				for (int i = 1; i < overlays; i++) {
					int offset = count * i + ((i != 1) ? (hasHat ? -4 : 0) : 0);
					
					if (i == 1 && hasHat) {
						ragdoll.addConnection(nbodyOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(njacketOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(narmsOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(narmsOffset + 1 + offset, bodyOffset, true, true);
						ragdoll.addConnection(narmsOffset + 2 + offset, bodyOffset, true, true);
						ragdoll.addConnection(nrightLegOffset + offset, rightLegOffset, true, true);
						ragdoll.addConnection(nleftLegOffset + offset, leftLegOffset, true, true);
					} else {
						ragdoll.addConnection(hatOffset + offset, headOffset, true, true);
						ragdoll.addConnection(hatRimOffset + offset, headOffset, true, true);
						ragdoll.addConnection(noseOffset + offset, headOffset, true, true);
						ragdoll.addConnection(headOffset + offset, headOffset, true, true);
						
						ragdoll.addConnection(bodyOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(jacketOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(armsOffset + offset, bodyOffset, true, true);
						ragdoll.addConnection(armsOffset + 1 + offset, bodyOffset, true, true);
						ragdoll.addConnection(armsOffset + 2 + offset, bodyOffset, true, true);
						ragdoll.addConnection(rightLegOffset + offset, rightLegOffset, true, true);
						ragdoll.addConnection(leftLegOffset + offset, leftLegOffset, true, true);
					}
				}
			}
		} else if (model instanceof IllagerEntityModel) {
			if (entity instanceof IllusionerEntity) {
				int headOffset = 0;
				int noseOffset = 1;
				int hatOffset = 2;
				int leftLegOffset = 3;
				int rightLegOffset = 4;
				int arms1Offset = 5;
				int arms2Offset = 6;
				int leftShoulderOffset = 7;
				int body1Offset = 8;
				int body2Offset = 9;

				ragdoll.addConnection(hatOffset, headOffset, true);
				ragdoll.addConnection(noseOffset, headOffset, true);
				ragdoll.addConnection(headOffset, body1Offset);
				ragdoll.addConnection(leftLegOffset, body1Offset);
				ragdoll.addConnection(arms1Offset, body1Offset, true);
				ragdoll.addConnection(rightLegOffset, body1Offset);
				ragdoll.addConnection(arms2Offset, body1Offset, true);
				ragdoll.addConnection(leftShoulderOffset, body1Offset, true);
				ragdoll.addConnection(body2Offset, body1Offset, true);
			} else if (entity instanceof EvokerEntity || entity instanceof VindicatorEntity) {
				int headOffset = 0;
				int noseOffset = 1;
				int leftLegOffset = 2;
				int rightLegOffset = 3;
				int arms1Offset = 4;
				int arms2Offset = 5;
				int leftShoulderOffset = 6;
				int body1Offset = 7;
				int body2Offset = 8;
	
				ragdoll.addConnection(noseOffset, headOffset, true);
				ragdoll.addConnection(headOffset, body1Offset);
				ragdoll.addConnection(leftLegOffset, body1Offset);
				ragdoll.addConnection(arms1Offset, body1Offset, true);
				ragdoll.addConnection(rightLegOffset, body1Offset);
				ragdoll.addConnection(arms2Offset, body1Offset, true);
				ragdoll.addConnection(leftShoulderOffset, body1Offset, true);
				ragdoll.addConnection(body2Offset, body1Offset, true);
			} else {
				int headOffset = 0;
				int noseOffset = 1;
				int leftLegOffset = 2;
				int rightArmOffset = 3;
				int rightLegOffset = 4;
				int leftArmOffset = 5;
				int bodyOffset = 6;
	
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
		} else if (model instanceof StriderEntityModel) {
			int leftLegOffset = 0;
			int rightLegOffset = 1;
			int bodyOffset = 2;

			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(3, bodyOffset, true);
			ragdoll.addConnection(4, bodyOffset, true);
			ragdoll.addConnection(5, bodyOffset, true);
			ragdoll.addConnection(6, bodyOffset, true);
			ragdoll.addConnection(7, bodyOffset, true);
			ragdoll.addConnection(8, bodyOffset, true);

			ragdoll.bodies.get(bodyOffset).backfaceCulling = false;
		} else if (model instanceof RavagerEntityModel) {
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
		} else if (model instanceof BatEntityModel) {
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
		} else if (model instanceof BeeEntityModel) {
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
		} else if (model instanceof RabbitEntityModel) {
			int headOffset;
			int noseOffset;
			int rightFrontLegOffset;
			int rightHindFootOffset;
			int tailOffset;
			int leftHaunchOffset;
			int rightHaunchOffset;
			int bodyOffset;
			int rightEarOffset;
			int leftFrontLegOffset;
			int leftHindFootOffset;
			int leftEarOffset;
			
			if (model.child) {
				headOffset = 0;
				leftEarOffset = 1;
				rightEarOffset = 2;
				noseOffset = 3;
				leftHindFootOffset = 4;
				rightHindFootOffset = 5;
				leftHaunchOffset = 6;
				rightHaunchOffset = 7;
				bodyOffset = 8;
				leftFrontLegOffset = 9;
				rightFrontLegOffset = 10;
				tailOffset = 11;
			} else {
				leftHindFootOffset = 0;
				rightHindFootOffset = 1;
				leftHaunchOffset = 2;
				rightHaunchOffset = 3;
				bodyOffset = 4;
				leftFrontLegOffset = 5;
				rightFrontLegOffset = 6;
				headOffset = 7;
				rightEarOffset = 8;
				leftEarOffset = 9;
				tailOffset = 10;
				noseOffset = 11;
			}

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
		} else if (model instanceof WitherEntityModel) {
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
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof OcelotEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int upperTailOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int lowerTailOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(upperTailOffset, bodyOffset);
			ragdoll.addConnection(lowerTailOffset, upperTailOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof FoxEntityModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int rightEarOffset = 2;
			int leftEarOffset = 3;
			int bodyOffset = 4;
			int tailOffset = 5;
			int leg1Offset = 6;
			int leg2Offset = 7;
			int leg3Offset = 8;
			int leg4Offset = 9;

			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(leg1Offset, bodyOffset);
			ragdoll.addConnection(leg2Offset, bodyOffset);
			ragdoll.addConnection(leg3Offset, bodyOffset);
			ragdoll.addConnection(leg4Offset, bodyOffset);
		} else if (model instanceof SilverfishEntityModel) {
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
		} else if (model instanceof EndermiteEntityModel) {
			int segment2Offset = 0;
			int segment1Offset = 1;
			int segment0Offset = 2;
			int segment3Offset = 3;

			ragdoll.addConnection(segment0Offset, segment1Offset);
			ragdoll.addConnection(segment1Offset, segment2Offset);
			ragdoll.addConnection(segment2Offset, segment3Offset);
		} else if (model instanceof ParrotEntityModel) {
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
		} else if (model instanceof HorseEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			boolean hasSaddle = ((ModelPart) animal.getHeadParts().iterator().next()).getChild("left_saddle_mouth").visible;
			int neckOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			if (model.child) {
				rightHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				leftHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				rightFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				leftFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
			
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
			
			int leftEarOffset = 2;
			int rightEarOffset = 3;
			int headOffset = 1;
			
			int mane = 4;
			int upperMouth = 5;
			int tail = 7;
			
			if (hasSaddle) {
				int leftSaddleMouthOffset = 4;
				int mouthSaddleWrapOffset = 5;
				// right saddle line and left saddle line are never used
				int rightSaddleLineOffset = 6;
				int rightSaddleMouthOffset = 7;
				int leftSaddleLineOffset = 8;
				int saddleOffset = 11;
				int headSaddleOffset = 9;
				upperMouth = 8;
				mane = 6;
				tail = 12;
				
				if (model instanceof DonkeyEntityModel) {
					boolean hasChests = ((AbstractDonkeyEntity) entity).hasChest();
					
					if (hasChests) {
						saddleOffset = 12;
						tail = 13;
					}
					
					// just ignore chest positions to make them pop off the model
//					int leftChest = 14;
//					int rightChest = 11;
				}

				ragdoll.addConnection(leftSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(mouthSaddleWrapOffset, neckOffset, true);
				ragdoll.addConnection(rightSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(headSaddleOffset, neckOffset, true);
				ragdoll.addConnection(saddleOffset, bodyOffset, true);
			} else {
				if (model instanceof DonkeyEntityModel) {
					boolean hasChests = ((AbstractDonkeyEntity) entity).hasChest();
					
					if (hasChests) {
						tail = 8;
					}
				}
			}
			
			ragdoll.addConnection(headOffset, neckOffset, true);
			ragdoll.addConnection(leftEarOffset, neckOffset, true);
			ragdoll.addConnection(rightEarOffset, neckOffset, true);
			
			ragdoll.addConnection(tail, bodyOffset);
			ragdoll.addConnection(mane, neckOffset, true);
			ragdoll.addConnection(upperMouth, neckOffset, true);
			
			if (RagdollMapper.countModelParts(entity, model) < ragdoll.bodies.size())
				ragdoll.addOverlayConnections(true);
		} else if (model instanceof LlamaEntityModel) {
			int headOffset = 0;
			int neckOffset = 1;
			int earLeftOffset = 2;
			int earRightOffset = 3;
			int bodyOffset = 4;
			int rightFrontLegOffset = 5;
			int rightHindLegOffset = 6;
			int leftHindLegOffset = 7;
			int leftFrontLegOffset = 8;
			
			// don't use chest positions to let them pop off the model
			int rightChestOffset = 9;
			int leftChestOffset = 10;
			
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
		} else if (model instanceof HoglinEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			int rightHornOffset = 1;
			int leftHornOffset = 2;
			int rightEarOffset = 3;
			int leftEarOffset = 4;
			int maneOffset = 6;
			
			ragdoll.addConnection(rightHornOffset, headOffset, true);
			ragdoll.addConnection(leftHornOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(maneOffset, bodyOffset, true);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof SalmonEntityModel) {
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
		} else if (model instanceof AxolotlEntityModel) {
			int bodyOffset = 0;
			int bodyGillsOffset = 1;
			int headOffset = 2;
			int topGillsOffset = 3;
			int leftGillsOffset = 4;
			int rightGillsOffset = 5;
			int rightFrontLegOffset = 6;
			int rightHindLegffset = 7;
			int tailOffset = 8;
			int leftHindLegOffset = 9;
			int leftFrontLegOffset = 10;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(bodyGillsOffset, bodyOffset, true);
			ragdoll.addConnection(topGillsOffset, bodyOffset, true);
			ragdoll.addConnection(leftGillsOffset, bodyOffset, true);
			ragdoll.addConnection(rightGillsOffset, bodyOffset, true);
			
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset, true);
			ragdoll.addConnection(rightHindLegffset, bodyOffset, true);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset, true);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true);
		} else if (model instanceof PhantomEntityModel) {
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
		} else if (model instanceof CodEntityModel) {
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
		} else if (model instanceof SmallPufferfishEntityModel) {
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
		} else if (model instanceof MediumPufferfishEntityModel) {
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
		} else if (model instanceof LargePufferfishEntityModel) {
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
		} else if (model instanceof LargeTropicalFishEntityModel) {
			int leftFinOffset = 0;
			int topFinOffset = 1;
			int tailOffset = 2;
			int rightFinOffset = 3;
			int bodyOffset = 4;
			int bottomFinOffset = 5;
			
			ragdoll.addConnection(leftFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(topFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(bottomFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true, true);
			
			ragdoll.addOverlayConnections(true);
		} else if (model instanceof SmallTropicalFishEntityModel) {
			int leftFinOffset = 0;
			int topFinOffset = 1;
			int tailOffset = 2;
			int rightFinOffset = 3;
			int bodyOffset = 4;
			
			ragdoll.addConnection(leftFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(topFinOffset, bodyOffset, true, true);
			ragdoll.addConnection(tailOffset, bodyOffset, true, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true, true);

			ragdoll.addOverlayConnections(true);
		} else if (entity instanceof EnderDragonEntity) {
			// ender dragon model is null because ender dragon has no living entity renderer and thus
			// we have no access to it's model
			for (int i = 0; i < 5; i++) {
				// do neck
				ragdoll.addConnection(i * 2 + 1, i * 2, true);
				
				if (i != 0) ragdoll.addConnection((i - 1) * 2, i * 2);
			}
			
			int offset = 10;

			int head0 = 0 + offset;
			int head1 = 1 + offset;
			int head2 = 2 + offset;
			int head3 = 3 + offset;
			int head4 = 4 + offset;
			int head5 = 5 + offset;
			int jaw = 6 + offset;
			
			ragdoll.addConnection(jaw, head0, true);
			ragdoll.addConnection(head1, head0, true);
			ragdoll.addConnection(head2, head0, true);
			ragdoll.addConnection(head3, head0, true);
			ragdoll.addConnection(head4, head0, true);
			ragdoll.addConnection(head5, head0, true);
			
			ragdoll.addConnection(head0, 8);

			int body0 = 7 + offset;
			int body1 = 8 + offset;
			int body2 = 9 + offset;
			int body3 = 10 + offset;
			
			int leftWing = 11 + offset;
			int leftWingTexture = 12 + offset;
			int leftWingTip = 13 + offset;
			int leftWingTipTexture = 14 + offset;
			int leftFrontLeg = 15 + offset;
			int leftFrontLegTip = 16 + offset;
			int leftFrontFoot = 17 + offset;
			int leftHindLeg = 18 + offset;
			int leftHindLegTip = 19 + offset;
			int leftHindFoot = 20 + offset;
			
			int rightWing = 21 + offset;
			int rightWingTexture = 22 + offset;
			int rightWingTip = 23 + offset;
			int rightWingTipTexture = 24 + offset;
			int rightFrontLeg = 25 + offset;
			int rightFrontLegTip = 26 + offset;
			int rightFrontFoot = 27 + offset;
			int rightHindLeg = 28 + offset;
			int rightHindLegTip = 29 + offset;
			int rightHindFoot = 30 + offset;
			
			ragdoll.addConnection(0, body0);
			
			ragdoll.addConnection(body1, body0, true);
			ragdoll.addConnection(body2, body0, true);
			ragdoll.addConnection(body3, body0, true);
			
			ragdoll.addConnection(rightWing, body0);
			ragdoll.addConnection(leftWing, body0);
			
			ragdoll.addConnection(rightWingTip, rightWing);
			ragdoll.addConnection(leftWingTip, leftWing);
			
			ragdoll.addConnection(rightWingTexture, rightWing, true);
			ragdoll.addConnection(leftWingTexture, leftWing, true);
			
			ragdoll.addConnection(rightWingTipTexture, rightWingTip, true);
			ragdoll.addConnection(leftWingTipTexture, leftWingTip, true);
			
			ragdoll.addConnection(rightFrontLeg, body0);
			ragdoll.addConnection(rightHindLeg, body0);
			ragdoll.addConnection(leftFrontLeg, body0);
			ragdoll.addConnection(leftHindLeg, body0);
			
			ragdoll.addConnection(rightFrontLegTip, rightFrontLeg);
			ragdoll.addConnection(rightHindLegTip, rightHindLeg);
			ragdoll.addConnection(leftFrontLegTip, leftFrontLeg);
			ragdoll.addConnection(leftHindLegTip, leftHindLeg);
			
			ragdoll.addConnection(rightFrontFoot, rightFrontLegTip);
			ragdoll.addConnection(rightHindFoot, rightHindLegTip);
			ragdoll.addConnection(leftFrontFoot, leftFrontLegTip);
			ragdoll.addConnection(leftHindFoot, leftHindLegTip);
			
			offset = 41;

			for (int i = 0; i < 12; i++) {
				// do tail
				ragdoll.addConnection(i * 2 + 1 + offset, i * 2 + offset, true);
				
				if (i != 0) ragdoll.addConnection((i - 1) * 2 + offset, i * 2 + offset);
			}
			
			ragdoll.addConnection(offset, body0);
		}
	}

	@Override
	public void filterCuboidsFromEntities(List<PhysicsEntity> blockifiedEntity, Entity entity, EntityModel model) {
		// when ragdolls are enabled you don't want to filter overlays
		// like the dots on horses
		boolean ragdollsEnabled = RagdollMapper.areRagdollsEnabled(entity);
		
		if (model instanceof IronGolemEntityModel) {
			while (blockifiedEntity.size() > 8) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SpiderEntityModel) {
			while (blockifiedEntity.size() > 11) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof StriderEntityModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof WitherEntityModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SheepEntityModel) {
			while (blockifiedEntity.size() > 6) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
//		} else if (model instanceof ZombieVillagerEntityModel) {
//			while (blockifiedEntity.size() > 10) {
//				blockifiedEntity.remove(blockifiedEntity.size() - 1);
//			}
		} else if (entity instanceof EnderDragonEntity) {
			// 31 for all the model parts and the neck and tail get rendered additionally (17 * 2)
			while (blockifiedEntity.size() > 31 + 17 * 2) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof LargeTropicalFishEntityModel || model instanceof SmallTropicalFishEntityModel || model instanceof SkeletonEntityModel || model instanceof HorseEntityModel ||
				model instanceof LlamaEntityModel || model instanceof DrownedEntityModel || model instanceof IllagerEntityModel || model instanceof VillagerResemblingModel || 
				model instanceof EndermanEntityModel) {
			int count = RagdollMapper.countModelParts(entity, model);
			
			if (!ragdollsEnabled) {
				while (blockifiedEntity.size() > count) {
					blockifiedEntity.remove(blockifiedEntity.size() - 1);
				}
			}
		} else if (model instanceof PhantomEntityModel || model instanceof ZombieEntityModel || model instanceof PiglinEntityModel) {
			int count = RagdollMapper.countModelParts(entity, model);
			
			while (blockifiedEntity.size() > count) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		}
	}

}
