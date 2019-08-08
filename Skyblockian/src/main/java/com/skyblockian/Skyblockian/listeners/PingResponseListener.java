package com.skyblockian.Skyblockian.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import com.skyblockian.Skyblockian.Skyblockian;

public class PingResponseListener 
implements Listener
{
	@SuppressWarnings("deprecation")
	public void addPingResponsePacketListener()
	{
		ProtocolLibrary.getProtocolManager().addPacketListener(
				new PacketAdapter(
						PacketAdapter.params(
								Skyblockian.getCore(),
								PacketType.Status.Server.OUT_SERVER_INFO)
						.serverSide()
						.gamePhase(GamePhase.BOTH)
						.listenerPriority(ListenerPriority.HIGHEST)
						.optionAsync()
		) {
			@Override
			public void onPacketSending(PacketEvent e)
			{
				try {
					WrappedServerPing ping = e.getPacket().getServerPings().getValues().get(0);
					
					String pingMessage = ChatColor.translateAlternateColorCodes('&', 
							Skyblockian.getCore().getConfig().getString("ping-message"));
					ping.setVersionProtocol(-1);
					ping.setVersionName(pingMessage);
					
					e.getPacket().getServerPings().getValues().set(0, ping);
				}catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
	}
}
