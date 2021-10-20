package com.formation;

import com.google.gwt.dom.client.Node;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public abstract class JQuery {
  public int length;

  @JsMethod(namespace = JsPackage.GLOBAL, name = "$")
  public static native JQuery $(Node node);

  public native JQuery find(String selector);
}
