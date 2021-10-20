package com.formation;

import com.google.gwt.user.cellview.client.CellTable;

public interface DefaultResources extends CellTable.Resources {
  @Override
  @Source({ "DefaultResources.gss" })
  MyStyle cellTableStyle();

  interface MyStyle extends CellTable.Style {}
}
