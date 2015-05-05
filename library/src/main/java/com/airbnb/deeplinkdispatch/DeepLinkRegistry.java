package com.airbnb.deeplinkdispatch;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeepLinkRegistry {

  private List<DeepLinkEntry> registry = new LinkedList<>();

  public DeepLinkRegistry(Loader loader) {
    if (loader != null) {
      loader.load(this);
    }
  }

  public void registerDeepLink(String uri, DeepLinkEntry.Type type, String activity,
                               String method) {
    registry.add(new DeepLinkEntry(uri, type, activity, method));
  }

  public DeepLinkEntry parseUri(String uriString) {
    for (DeepLinkEntry entry : registry) {
      Matcher matcher = Pattern.compile(entry.getRegex()).matcher(uriString);
      if (matcher.matches()) {
        return entry;
      }
    }

    return null;
  }
}
