package me.eonexe.chungus.hack.util;

import net.minecraftforge.fml.common.FMLLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.eonexe.chungus.Chungus;

public class NetworkUtil {

    public static List<String> getHWIDList(){
        List<String> HWIDList = new ArrayList<>();
        try {
            final URL url = new URL(Chungus.HWID_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                HWIDList.add(inputLine);
            }
        } catch(Exception e) {
            FMLLog.log.info("Load HWID Failed!");
        }
        return HWIDList;
    }
}