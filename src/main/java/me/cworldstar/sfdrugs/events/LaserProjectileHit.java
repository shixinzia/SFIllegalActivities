package me.cworldstar.sfdrugs.events;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.dot.Decay;

public class LaserProjectileHit implements Listener {
	private SFDrugs plugin;
    public LaserProjectileHit(SFDrugs plugin) {
    	this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	@EventHandler
	private void onProjectileHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Projectile & e.getDamager().hasMetadata("SFDRUGS_IS_LASER_PROJECTILE")) {
            if(e.getEntity() instanceof LivingEntity) {
            	LivingEntity Entity = (LivingEntity) e.getEntity();
            	Entity.damage(10, e.getDamager());
            	new Decay(Entity, plugin);
            }
        }
	}
    @EventHandler
    public void onProjectileHitBlock(ProjectileHitEvent e) {
        if (e.getEntity().hasMetadata("SFDRUGS_IS_LASER_PROJECTILE") && e.getHitBlock() != null) {
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 4F, true, true);
        }
    }
}
