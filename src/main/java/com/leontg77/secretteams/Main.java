/*
 * Project: SecretTeams
 * Class: com.leontg77.secretteams.Main
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Leon Vaktskjold <leontg77@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.leontg77.secretteams;

import com.google.common.collect.Sets;
import com.leontg77.secretteams.commands.SecretTeamsCommand;
import com.leontg77.secretteams.listener.KillListener;
import com.leontg77.secretteams.protocol.TeamUpdateAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.UUID;

/**
 * The main class of the plugin.
 *
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
    public static final String PREFIX = "§cSecret Teams §8» §7";

    public final Set<UUID> hasAKill = Sets.newHashSet();

    @Override
    public void onEnable() {
        reloadConfig();

        getConfig().addDefault("enableKillReveal", false);
        getConfig().options().copyDefaults(true);

        saveConfig();

        TeamManager teams = new TeamManager();

        TeamUpdateAdapter adapter = new TeamUpdateAdapter(this);
        KillListener listener = new KillListener(this, teams);

        SecretTeamsCommand cmd = new SecretTeamsCommand(this, listener, adapter);

        // register command.
        getCommand("secretteams").setExecutor(cmd);
    }

    /**
     * Send the given message to all online players.
     *
     * @param message The message to send.
     */
    public void broadcast(String message) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.sendMessage(message);
        }

        Bukkit.getLogger().info(message);
    }
}