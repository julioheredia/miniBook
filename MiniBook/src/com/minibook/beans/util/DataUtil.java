package com.minibook.beans.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DataUtil {

  public static final Integer DIF_ANOS = Integer.valueOf(99);

  public static List<String> getDiasMes() {
    List<String> dias = new ArrayList<String>();
    dias.add("Dia");
    for (Integer i = 1; i <= 31; i++) {
      dias.add(i.toString());
    }
    return dias;
  }

  public static List<String> getMesesAno() {
    List<String> meses = new ArrayList<String>();
    meses.add("Mês");
    for (Integer i = 1; i <= 12; i++) {
      meses.add(i.toString());
    }
    return meses;
  }


  public static List<String> getPeridoAnos() {
    List<String> a = new ArrayList<String>();
    a.add("Ano");
    Calendar cal = GregorianCalendar.getInstance();

    for (Integer i = cal.get(Calendar.YEAR); i >= (cal.get(Calendar.YEAR) - DIF_ANOS); i--) {
      a.add(i.toString());
    }
    return a;
  }

}
