package com.minibook.beans.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LocaleUtil {

  public static List<String> getPaises() {

    String[] locales = Locale.getISOCountries();
    List<String> list = new ArrayList<String>();
    for (String countryCode : locales) {
      Locale obj = new Locale("", countryCode);
      list.add(obj.getDisplayCountry().toString());
    }

    Collections.sort(list);

    return list;
  }

  public static List<String> getEstados() {
    List<Estado> locales = Arrays.asList(Estado.values());
    List<String> list = new ArrayList<String>();
    for (Estado estado : locales) {
      list.add(estado.toString());
    }

    Collections.sort(list);

    return list;
  }

}
