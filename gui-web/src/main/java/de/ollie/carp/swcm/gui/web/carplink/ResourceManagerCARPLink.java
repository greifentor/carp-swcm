package de.ollie.carp.swcm.gui.web.carplink;

import javax.inject.Inject;
import javax.inject.Named;

import de.ollie.carp.corelib.localization.LocalizationSO;
import de.ollie.carp.swcm.gui.web.go.LocalizationGO;
import de.ollie.carp.swcm.gui.web.port.ResourceManager;

/**
 * A link to use the resource manager from the CARP corelib in the SWCM application.
 *
 * @author ollie (15.09.2021)
 */
@Named
public class ResourceManagerCARPLink implements ResourceManager {

	@Inject
	private de.ollie.carp.corelib.localization.ResourceManager carpResourceManager;

	@Override
	public String getLocalizedString(String resourceId) {
		return carpResourceManager.getLocalizedString(resourceId);
	}

	@Override
	public String getLocalizedString(String resourceId, LocalizationGO localization) {
		return carpResourceManager.getLocalizedString(resourceId, toSO(localization));
	}

	private LocalizationSO toSO(LocalizationGO go) {
		return LocalizationSO.valueOf(go.name());
	}

}
