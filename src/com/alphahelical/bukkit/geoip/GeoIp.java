/**
 * 
 */
package com.alphahelical.bukkit.geoip;

import java.net.InetAddress;
import org.bukkit.Bukkit;
import uk.org.whoami.geoip.GeoIPTools;

/**
 * @author kbeckman
 *
 */
public class GeoIp {
	
	private static String pluginName = "GeoIPTools";

	private static GeoIp instance;
	private static GeoIp getInstance() {
		if (instance == null) reload();
		return instance;
	}
	private static void setInstance(GeoIp instance) {
		GeoIp.instance = instance;
	}
	
	private GeoIPTools plugin = null;
	private GeoIPTools getPlugin() {
		return this.plugin;
	}
	private void setPlugin(GeoIPTools plugin) {
		this.plugin = plugin;
	}
	
	private GeoIp() {
		setInstance(this);
		this.setPlugin((GeoIPTools)Bukkit.getServer().getPluginManager().getPlugin(pluginName));
	}
	
	public static void reload() {
		new GeoIp();
	}
	
	public static String countryCodeFromAddress(final InetAddress addr) {
		String country_code = getInstance().getPlugin().getGeoIPLookup().getCountry(addr).getCode();
		country_code = (country_code.equalsIgnoreCase("--") ? null : country_code);
		return country_code;
	}


	
}
