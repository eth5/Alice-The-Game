package com.mygdx.game.ext.core.actor.interfaces;

@FunctionalInterface
public interface Action
{
 void invoke();
 @FunctionalInterface
 interface Arg1<T>
 {
  void invoke(T arg);
 }
 @FunctionalInterface
 interface Arg2<T1,T2>
 {
  void invoke(T1 arg, T2 arg2);
 }
 @FunctionalInterface
 interface Arg3<T1,T2,T3>
 {
  void invoke(T1 arg, T2 arg2, T3 arg3);
 }
 @FunctionalInterface
 interface Arg4<T1,T2,T3,T4>
 {
  void invoke(T1 arg, T2 arg2, T3 arg3, T4 arg4);
 }
}