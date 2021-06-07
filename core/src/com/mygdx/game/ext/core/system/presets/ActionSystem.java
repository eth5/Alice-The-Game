package com.mygdx.game.ext.core.system.presets;

import com.mygdx.game.ext.core.components.presets.ActionComponent;
import com.mygdx.game.ext.core.system.System;

public class ActionSystem extends System
{
 public ActionSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 1;
 }
 private ActionComponent actionComponent;
 protected void loadFields()
 {
  actionComponent = ActionComponent.get(actor);
 }

 protected void behave()
 {
  actionComponent.action.invoke(actor);
 }
}
