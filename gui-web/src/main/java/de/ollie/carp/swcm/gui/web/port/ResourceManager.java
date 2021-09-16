package de.ollie.carp.swcm.gui.web.port;

import de.ollie.carp.swcm.gui.web.go.LocalizationGO;

/**
 * An interface for a resource manager in web layer.
 *
 * @author ollie (15.09.2021)
 */
public interface ResourceManager {

	/**
	 * Returns a localized string for the passed resource id string (standard localization).
	 * 
	 * @param resourceId The resource id string of the requested resource.
	 * @return The localized string of the requested resource or the resource id if no localized string is found for the
	 *         resource id.
	 */
	String getLocalizedString(String resourceId);

	/**
	 * Returns a localized string for the passed resource id string.
	 * 
	 * @param resourceId   The resource id string of the requested resource.
	 * @param localization The localization which the string is to return for.
	 * @return The localized string of the requested resource or the resource id if no localized string is found for the
	 *         resource id.
	 */
	String getLocalizedString(String resourceId, LocalizationGO localization);

}