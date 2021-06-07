package com.mygdx.game.ext.core.system.presets.collisionSystem;

public class CollisionType
{
 public static final int NONE = 0; // не реагирует на коллизии, нужно только для теста
 public static final int LIQUID = 1; // жидкость (замедление времени)
 public static final int SOLID = 2; // стены, итд
 public static final int BODY = 3; // игрок, нпс, итд, все живое **
 public static final int PLATFORM = 4; // Платформа, ведет себя как Solid только при контакте сверху

 /*
 ненужные: (не нужна реализация)
 LIQUID+LIQUID
 SOLID+LIQUID (только если у нас вода движется, но нет)
 SOLID+SOLID

 BODY+SOLID = handleBodySolid(); актера body выталкивает вектором из solid
 BODY+LIQUID = handleBodyLiquid(); актер body может спокойно перемещаться по liquid с меньшей скоростью
 BODY+BODY = handleBodyBody(); ничего не происходит, но так как коллизия может произойти, в отличие от тех же (liquid+liquid), хэндл-метод необходим
  */
}
