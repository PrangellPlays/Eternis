package dev.lumi.eternis;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class EternisPreLaunchEntrypoint implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        System.setProperty("devauth.enabled", "true");
        System.setProperty("devauth.account", "main");
    }
}
