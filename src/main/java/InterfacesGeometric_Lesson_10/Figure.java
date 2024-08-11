package InterfacesGeometric_Lesson_10;

public interface Figure {
    double calculateArea();
    double calculatePerimeter();

    String getFillColor();
    void setFillColor(String fillColor);

    String getBorderColor();
    void setBorderColor(String borderColor);
}
