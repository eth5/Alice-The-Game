package com.mygdx.game.ext.core.actor.interfaces;

@FunctionalInterface
public interface Func<TResult>
{
 TResult invoke();
 @FunctionalInterface
 interface Arg1<T,TResult>
 {
  TResult invoke(T arg);
 }
 @FunctionalInterface
 interface Arg2<T1,T2,TResult>
 {
  TResult invoke(T1 arg1, T2 arg2);
 }
 @FunctionalInterface
 interface Arg3<T1,T2,T3,TResult>
 {
  TResult invoke(T1 arg1, T2 arg2, T3 arg3);
 }
 @FunctionalInterface
 interface Arg4<T1,T2,T3,T4,TResult>
 {
  TResult invoke(T1 arg1, T2 arg2, T3 arg3, T4 arg4);
 }

}