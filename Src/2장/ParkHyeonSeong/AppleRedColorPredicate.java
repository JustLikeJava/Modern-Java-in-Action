package javaStudy_chapter2;

public class AppleRedColorPredicate implements ApplePredicate{
	public boolean test(Apple apple) {
		return "red".equals(apple.getColor());
	}
}

