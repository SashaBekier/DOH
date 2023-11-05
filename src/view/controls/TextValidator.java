package view.controls;

@FunctionalInterface
public interface TextValidator {
	public boolean validate(String text);
}
