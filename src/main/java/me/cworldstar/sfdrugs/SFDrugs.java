package me.cworldstar.sfdrugs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.cworldstar.sfdrugs.events.CorporationTraderEvent;
import me.cworldstar.sfdrugs.events.CustomMobDeathEvent;
import me.cworldstar.sfdrugs.events.DrugSuitDamaged;
import me.cworldstar.sfdrugs.events.DrugSuitWearerDamaged;
import me.cworldstar.sfdrugs.events.GangMemberSpawnEvent;
import me.cworldstar.sfdrugs.events.LaserProjectileHit;
import me.cworldstar.sfdrugs.events.MysteriousTraderEvent;
import me.cworldstar.sfdrugs.events.PlayerAddedEvent;
import me.cworldstar.sfdrugs.events.RobotArmorDamaged;
import me.cworldstar.sfdrugs.events.RobotArmorPieceEquipped;
import me.cworldstar.sfdrugs.events.SFHookerEvent;
import me.cworldstar.sfdrugs.events.UnstableObjectEvent;
import me.cworldstar.sfdrugs.implementations.commands.TestCorporationEnemy;
import me.cworldstar.sfdrugs.implementations.events.ArmorListener;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Trading;
import net.guizhanss.guizhanlibplugin.updater.GuizhanBuildsUpdaterWrapper;
public class SFDrugs extends AbstractAddon implements SlimefunAddon {
    public SFDrugs(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file,
                "Sniperkaos", "SFIllegalActivities", "master", "auto-update");
	}

    
    public SFDrugs() {
    	super("Sniperkaos","SFIllegalActivities","master","auto-update");
    }    
    
    @SuppressWarnings("unused")
	@Override
	public void enable() {
		if (getConfig().getBoolean("options.auto-update") && // 注意这里，如果config.yml中直接是`auto-config`那就得把前面的`options.`去掉
				getDescription().getVersion().startsWith("Build")) { // 如果你修改了版本格式，按需修改。你也可以去除这一部分
			new GuizhanBuildsUpdater(this, getFile(), "SlimefunGuguProject", "SFIllegalActivities", "master", false, "zh-CN").start(); // 必须修改
		}
    	Items ItemRegistry = new Items(this);
		getServer().getPluginManager().registerEvents(new ArmorListener(new ArrayList<String>()), this);
		CustomMobDeathEvent DeathEvent = new CustomMobDeathEvent(this);
    	DrugSuitDamaged DamageEvent = new DrugSuitDamaged(this);
    	DrugSuitWearerDamaged DamageEvent2 = new DrugSuitWearerDamaged(this);
    	ItemRegistry.register();
    	Trading TradingRegistry = new Trading(this);
    	TradingRegistry.register();
    	RandomUtils ThisIsSoStupid = new RandomUtils();
    	
    	/**
    	 * 
    	 * Implementation for UnstableObjects.
    	 * 
    	 */
    	PlayerAddedEvent WhoEvenReadsThese = new PlayerAddedEvent(this);
    	UnstableObjectEvent IDont = new UnstableObjectEvent(this);
    	SFHookerEvent HookerEvent = new SFHookerEvent(this,TradingRegistry);
    	CorporationTraderEvent TraderEvent = new CorporationTraderEvent(this,TradingRegistry);
    	MysteriousTraderEvent TraderEvent2 = new MysteriousTraderEvent(this,TradingRegistry);
    	RobotArmorDamaged RobotArmorEvent = new RobotArmorDamaged(this);
    	RobotArmorPieceEquipped RobotArmorPieceEquipped = new RobotArmorPieceEquipped(this);
    	LaserProjectileHit LaserProjectileHitEvent = new LaserProjectileHit(this);
    	GangMemberSpawnEvent GangMemberSpawn = new GangMemberSpawnEvent(this);
    	TestCorporationEnemy Command = new TestCorporationEnemy(this);
    	this.getCommand("test").setExecutor(Command);
    	Logger x = getLogger();
    	x.log(Level.INFO, "============================================");
    	x.log(Level.INFO, "====                                     ===");
    	x.log(Level.INFO, "====         SF DRUGS ENABLED            ===");
    	x.log(Level.INFO, "====             v ".concat(this.getPluginVersion()).concat("                 ==="));
    	x.log(Level.INFO, "====         by China Worldstar          ===");
    	x.log(Level.INFO, "====                                     ===");
    	x.log(Level.INFO, "============================================");
    }
    @Override
    public void disable() {}

}
