package de.ollie.carp.swcm.gui.web;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

// @Push
@PWA(name = "CARP for Star Wars", shortName = "CARP-SWCM", enableInstallPrompt = false)
public class AppShell implements AppShellConfigurator {
}
