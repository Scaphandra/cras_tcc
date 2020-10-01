package gui.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MaskFieldUtil extends TextField{
	
	private static List<KeyCode> ignoreKeyCodes = new ArrayList<>();

    public static void ignoreKeys(TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_PRESSED, (EventHandler) new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent keyEvent) {
                if (ignoreKeyCodes.contains(keyEvent.getCode())) {
                    keyEvent.consume();
                }
            }
        });
    }

    //xxxxx-xxxxx-xxxxx-xxxxx
    public static void serialTextField(final TextField textField) {
        MaskFieldUtil.maxField(textField, 23);
        textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() < 24) {
                String value = textField.getText();
                value = value.replaceAll("[^\\w]", "");
                value = value.replaceFirst("(\\w{5})(\\w)", "$1-$2");
                value = value.replaceFirst("(\\w{5})\\-(\\w{5})(\\w)", "$1-$2-$3");
                value = value.replaceFirst("(\\w{5})\\-(\\w{5})\\-(\\w{5})(\\w)", "$1-$2-$3-$4");
                textField.setText(value.toUpperCase());
                MaskFieldUtil.positionCaret(textField);
            }
        });
    }

    public static void dateField(final TextField textField) {
        MaskFieldUtil.maxField(textField, 10);
        textField.lengthProperty().addListener((ChangeListener) new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() < 11) {
                    String value = textField.getText();
                    value = value.replaceAll("[^0-9]", "");
                    value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
                    value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
                    textField.setText(value);
                    MaskFieldUtil.positionCaret(textField);
                }
            }
        });
    }

    public static void numericField(final TextField textField) {
        textField.lengthProperty().addListener((ChangeListener) new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                char ch;
                if (newValue.intValue() > oldValue.intValue() && ((ch = textField.getText().charAt(oldValue.intValue())) < '0' || ch > '9')) {
                    textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
                }
            }
        });
    }

    public static void monetaryField(final TextField textField) {
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
            value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
            textField.setText(value);
            MaskFieldUtil.positionCaret(textField);
            textField.textProperty().addListener((ChangeListener) new ChangeListener<String>() {

                public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                    if (newValue.length() > 17) {
                        textField.setText(oldValue);
                    }
                }
            });
        }
        );
        textField.focusedProperty().addListener((observableValue, aBoolean, fieldChange) -> {
            int length;
            if (!(fieldChange || (length = textField.getText().length()) <= 0 || length >= 3)) {
                textField.setText(textField.getText() + "00");
            }
        }
        );
    }

    public static BigDecimal monetaryValueFromField(TextField textField) {
        if (textField.getText().isEmpty()) {
            return null;
        }
        BigDecimal retorno = BigDecimal.ZERO;
        NumberFormat nf = NumberFormat.getNumberInstance();
        try {
            Number parsedNumber = nf.parse(textField.getText());
            retorno = new BigDecimal(parsedNumber.toString());
        } catch (ParseException ex) {
            Logger.getLogger(MaskFieldUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static void cpfCnpjField(TextField textField) {
        MaskFieldUtil.maxField(textField, 18);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            String value = textField.getText();
            if (number2.intValue() <= 14) {
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
            } else {
                value = value.replaceAll("[^0-9]", "");
                value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
                value = value.replaceFirst("(\\d{3})(\\d)", "$1/$2");
                value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
            }
            textField.setText(value);
            MaskFieldUtil.positionCaret(textField);
        }
        );
    }

    public static void cepField(TextField textField) {
        MaskFieldUtil.maxField(textField, 9);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
            textField.setText(value);
            MaskFieldUtil.positionCaret(textField);
        }
        );
    }

    public static void foneField(TextField textField) {
        MaskFieldUtil.maxField(textField, 14);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            try {
                String value = textField.getText();
                value = value.replaceAll("[^0-9]", "");
                int tam = value.length();
                value = value.replaceFirst("(\\d{2})(\\d)", "($1)$2");
                value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
                if (tam > 10) {
                    value = value.replaceAll("-", "");
                    value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
                }
                textField.setText(value);
                MaskFieldUtil.positionCaret(textField);

            } catch (Exception ex) {
            }
        }
        );
    }

    public static void cpfField(TextField textField) {
        MaskFieldUtil.maxField(textField, 14);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
            try {
            textField.setText(value);
            MaskFieldUtil.positionCaret(textField);
            }catch(Exception ex){}
        }
        );
    }

    public static void cnpjField(TextField textField) {
        MaskFieldUtil.maxField(textField, 18);
        textField.lengthProperty().addListener((observableValue, number, number2) -> {
            String value = textField.getText();
            value = value.replaceAll("[^0-9]", "");
            value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
            value = value.replaceFirst("(\\d{3})(\\d)", "$1/$2");
            value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
            textField.setText(value);
            MaskFieldUtil.positionCaret(textField);
        }
        );
    }

    private static void positionCaret(TextField textField) {
        Platform.runLater(() -> {
            if (textField.getText().length() != 0) {
                textField.positionCaret(textField.getText().length());
            }
        }
        );
    }

    public static void maxField(TextField textField, Integer length) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > length) {
                textField.setText(oldValue);
            }
        }
        );
    }

    public static String onlyDigitsValue(TextField field) {
        String result = field.getText();
        if (result == null) {
            return null;
        }
        return result.replaceAll("[^0-9]", "");
    }

    public static String onlyAlfaNumericValue(TextField field) {
        String result = field.getText();
        if (result == null) {
            return null;
        }
        return result.replaceAll("[^0-9]", "");
    }

    static {
        Collections.addAll(ignoreKeyCodes, new KeyCode[]{KeyCode.F1, KeyCode.F2, KeyCode.F3, KeyCode.F4, KeyCode.F5, KeyCode.F6, KeyCode.F7, KeyCode.F8, KeyCode.F9, KeyCode.F10, KeyCode.F11, KeyCode.F12});
    }
	
//	private String mask;
//	private ArrayList<String> patterns;
//	
//	public MaskTextField() {
//		super();
//		patterns = new ArrayList<String>();
//	}
//	public MaskTextField(String text) {
//		super(text);
//		patterns = new ArrayList<String>();
//	}
//	
//	@Override
//	public void replaceText(int start, int end, String text) {
//		
//		String tempText = this.getText()+text;
//		
//		if(mask == null || mask.length()==0) {
//			super.replaceText(start, end, text);
//		}else if (tempText.matches(this.mask) || tempText.length() == 0) {        //text.length == 0 representa o delete ou backspace
//
//            super.replaceText(start, end, text);
//
//        } else {
//
//            String tempP = "^";
//
//            for (String patt : this.patterns) {
//
//                tempP += patt;
//
//                if (tempText.matches(tempP)) {
//
//                    super.replaceText(start, end, text);
//                    break;
//
//                }
//
//            }
//
//        }
//
//    }
//
//    /**
//     * @return the Regex Mask
//     */
//    public String getMask() {
//        return mask;
//    }
//
//    /**
//     * @param mask the mask to set
//     */
//    public void setMask(String mask) {
//
//        String tempMask = "^";
//
//        for (int i = 0; i < mask.length(); ++i) {
//
//            char temp = mask.charAt(i);
//            String regex;
//            int counter = 1;
//            int step = 0;
//
//            if (i < mask.length() - 1) {
//                for (int j = i + 1; j < mask.length(); ++j) {
//                    if (temp == mask.charAt(j)) {
//                        ++counter;
//                        step = j;
//                    } else if (mask.charAt(j) == '!') {
//                        counter = -1;
//                        step = j;
//                    } else {
//                        break;
//                    }
//                }
//            }
//            switch (temp) {
//
//                case '*':
//                    regex = ".";
//                    break;
//                case 'S':
//                    regex = "[^\\s]";
//                    break;
//                case 'P':
//                    regex = "[A-z.]";
//                    break;
//                case 'M':
//                    regex = "[0-z.]";
//                    break;
//                case 'A':
//                    regex = "[0-z]";
//                    break;
//                case 'N':
//                    regex = "[0-9]";
//                    break;
//                case 'L':
//                    regex = "[A-z]";
//                    break;
//                case 'U':
//                    regex = "[A-Z]";
//                    break;
//                case 'l':
//                    regex = "[a-z]";
//                    break;
//                case '.':
//                    regex = "\\.";
//                    break;
//                default:
//                    regex = Character.toString(temp);
//                    break;
//
//            }
//
//            if (counter != 1) {
//
//                this.patterns.add(regex);
//
//                if (counter == -1) {
//                    regex += "+";
//                    this.patterns.add(regex);
//                } else {
//                    String tempRegex = regex;
//                    for (int k = 1; k < counter; ++k) {
//                        regex += tempRegex;
//                        this.patterns.add(tempRegex);
//                    }
//                }
//
//                i = step;
//
//            } else {
//                this.patterns.add(regex);
//            }
//
//            tempMask += regex;
//
//        }
//
//        this.mask = tempMask + "$";
//
//    }
}
