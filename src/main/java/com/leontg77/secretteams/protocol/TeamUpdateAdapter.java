/*
 * Project: SecretTeams
 * Class: com.leontg77.secretteams.protocol.TeamUpdateAdapter
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

package com.leontg77.secretteams.protocol;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.leontg77.secretteams.Main;
import org.bukkit.Bukkit;

import java.util.Arrays;

/**
 * Team update adapter class.
 *
 * @author LeonTG77
 */
public class TeamUpdateAdapter extends PacketAdapter {

    public TeamUpdateAdapter(Main plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.SCOREBOARD_TEAM);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (!event.getPacketType().equals(PacketType.Play.Server.SCOREBOARD_TEAM)) {
            return;
        }

        Bukkit.broadcastMessage(event.getPacket().getBytes().read(0) + "");
        Bukkit.broadcastMessage(Arrays.toString(event.getPacket().getStringArrays().read(0)) + "");
    }
}