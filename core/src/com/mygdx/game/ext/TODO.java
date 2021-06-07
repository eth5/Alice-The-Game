package com.mygdx.game.ext;

public class TODO
{
 // TODO : Напомнить о баге в библиотеке при зажиме и расфокусе
 //  updateinGameTime method
 //  replace JumpComponent and everything else with only movementComponent
 //  semi-fixed step
 //  Найти ошибку в формуле JumpComponent +use formula from saved, and separate gravity from jumping
 //  use priority list instead of int;
 //  set flipX in Graphics, not in Physics
 //  Наращивать скорость при старте ходьбы, а не сразу же подавать максимальную. Таким образом увелиичим микроуправляемость
 //  Системы в отдельный класс Systems и в движок. a-la Color presets в классе Color
 //  Кластер объединение справа, сверху, слева, снизу по сто блоков, образуется кластер, проверка position.x overlaps происходит именно у него, при запросе проверки у разных актеров они все ссылаются на один кластер
 //  CollisionMaster DrawingMaster (Assigned Actor Controller) https://www.youtube.com/watch?v=bRW-4cJaiJM
 /**
  . ., [27.03.21 00:42]
  assetmanager, вращение gear, и draw=false текстуры в ее компоненте, если она на 30 блоков от мастера (алисы). Мастера задать через drawingSystem.setMaster()

  . ., [27.03.21 01:39]
  stand animation

  . ., [27.03.21 01:41]
  движок не подходит, слои, мастер

  */

 // ECS ENTITY BUILDER
 // REMOVE ASSIGNED ACTORS FROM SYSTEMS . USE EVENT-ALIKE FOR SYSTEMS WITHOUT COMPONENT
 // TODO: UNECCESARY SYSTEMS PRIORITY LSIT (now its just must-have priority number)
}
