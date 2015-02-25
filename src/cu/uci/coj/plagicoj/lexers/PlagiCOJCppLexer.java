/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.plagicoj.lexers;

/**
 *
 * @author Leandro
 */
// $ANTLR 3.4 D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g 2012-02-24 22:33:16
import java.io.File;
import java.io.IOException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PlagiCOJCppLexer extends Lexer {

    public static final int EOF = -1;
    public static final int FOR = 300;
    public static final int WHILE = 301;
    public static final int SWITCH = 351;
    
    public static final int AMPERSAND = 4;
    public static final int AND = 5;
    public static final int ARGS = 6;
    public static final int ASSIGNEQUAL = 7;
    public static final int BITWISEANDEQUAL = 8;
    public static final int BITWISEOR = 9;
    public static final int BITWISEOREQUAL = 10;
    public static final int BITWISEXOR = 11;
    public static final int BITWISEXOREQUAL = 12;
    public static final int CHARACTER_LITERAL = 13;
    public static final int CKEYWORD = 14;
    public static final int COLON = 15;
    public static final int COMMA = 16;
    public static final int COMMENT = 1026;
    public static final int CONCATENATE = 18;
    public static final int COPERATOR = 19;
    public static final int DECIMAL_LITERAL = 20;
    public static final int DEFINE = 21;
    public static final int DEFINED = 22;
    public static final int DIRECTIVE = 23;
    public static final int DIVIDE = 24;
    public static final int DIVIDEEQUAL = 25;
    public static final int DOT = 1031;
    public static final int DOTMBR = 27;
    public static final int ELIF = 350;
    public static final int ELLIPSIS = 29;
    public static final int ELSE = 30;
    public static final int ENDIF = 31;
    public static final int EQUALITY_OPERATOR = 100;
    public static final int ERROR = 33;
    public static final int ESCAPED_NEWLINE = 34;
    public static final int EXEC_MACRO = 35;
    public static final int EXPAND = 36;
    public static final int EXPR = 37;
    public static final int EXPR_DEF = 38;
    public static final int EXPR_GROUP = 39;
    public static final int EXPR_NDEF = 40;
    public static final int EXP_ARG = 41;
    public static final int EXP_ARGS = 42;
    public static final int End = 43;
    public static final int EscapeSequence = 44;
    public static final int Exponent = 45;
    public static final int FLOATING_POINT_LITERAL = 46;
    public static final int FloatTypeSuffix = 47;
    public static final int GREATER_THAN_OPERATOR = 103;
    public static final int GREATER_OR_EQUAL_OPERATOR = 105;
    public static final int HEX_LITERAL = 50;
    public static final int HexDigit = 51;
    public static final int IDENTIFIER = 1000;
    public static final int IF = 53;
    public static final int IFDEF = 54;
    public static final int IFNDEF = 55;
    public static final int INCLUDE = 56;
    public static final int INCLUDE_EXPAND = 57;
    public static final int INCLUDE_FILE = 58;
    public static final int INDEX_OP = 59;
    public static final int IntegerTypeSuffix = 60;
    public static final int LCURLY = 61;
    public static final int LOWER_THAN_OPERATOR = 102;
    public static final int LESS_OR_EQUAL_OPERATOR = 104;
    public static final int LETTER = 64;
    public static final int LINE = 65;
    public static final int LINE_COMMENT = 66;
    public static final int LPAREN = 67;
    public static final int LSQUARE = 68;
    public static final int MACRO_DEFINE = 69;
    public static final int MACRO_TEXT = 70;
    public static final int MAC_FUNCTION = 71;
    public static final int MAC_FUNCTION_OBJECT = 72;
    public static final int MAC_OBJECT = 73;
    public static final int METHOD_CALL = 74;
    public static final int MINUS = 75;
    public static final int MINUSEQUAL = 76;
    public static final int MINUSMINUS = 77;
    public static final int MOD = 78;
    public static final int MODEQUAL = 79;
    public static final int NEGATION_OPERATOR = 106;
    public static final int INEQUALITY_OPERATOR = 101;
    public static final int OCTAL_LITERAL = 82;
    public static final int OR = 83;
    public static final int OctalEscape = 84;
    public static final int PLUS = 85;
    public static final int PLUSEQUAL = 86;
    public static final int PLUSPLUS = 87;
    public static final int POINTER = 88;
    public static final int POINTERTO = 89;
    public static final int POINTERTOMBR = 90;
    public static final int POINTER_AT = 91;
    public static final int POST_DEC = 92;
    public static final int POST_INC = 93;
    public static final int PRAGMA = 94;
    public static final int QUESTIONMARK = 95;
    public static final int RCURLY = 96;
    public static final int REFERANCE = 97;
    public static final int RPAREN = 98;
    public static final int RSQUARE = 99;
    public static final int SCOPE = 100;
    public static final int SEMICOLON = 101;
    public static final int SHARPSHARP = 102;
    public static final int SHIFTLEFT = 103;
    public static final int SHIFTLEFTEQUAL = 104;
    public static final int SHIFTRIGHT = 105;
    public static final int SHIFTRIGHTEQUAL = 106;
    public static final int SIZEOF = 107;
    public static final int SIZEOF_TYPE = 108;
    public static final int STAR = 109;
    public static final int STRINGIFICATION = 110;
    public static final int STRING_LITERAL = 1100;
    public static final int STRING_OP = 112;
    public static final int TEXT_END = 113;
    public static final int TEXT_GROUP = 114;
    public static final int TEXT_LINE = 115;
    public static final int TILDE = 116;
    public static final int TIMESEQUAL = 117;
    public static final int TYPECAST = 118;
    public static final int UNARY_MINUS = 119;
    public static final int UNARY_PLUS = 120;
    public static final int UNDEF = 121;
    public static final int UnicodeEscape = 122;
    public static final int WARNING = 123;
    public static final int WS = 124;
    boolean inDirective = false;
    boolean inDefineMode = false;
    boolean discardSpace = true;
    int ltoken = End;
    static char cpp = 'X';
    static String[] includeSearchPaths = {"/usr/local/include",
        "/usr/lib/gcc/i386-redhat-linux/4.0.0/include",
        "/usr/include/linux/stddef.h",
        "/usr/include"};

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[]{};
    }

    public PlagiCOJCppLexer() {
    }

    public PlagiCOJCppLexer(CharStream input) {
        super(input);
    }

    public String getGrammarFileName() {
        return "D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g";
    }
public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\C.g:67:7: ( 'switch' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\C.g:67:9: 'switch'
            {
            match("switch"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
           
            {
                match("for");


            }
            type = _type;
            channel = _channel;
        } finally {
        }
    }
    
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:39:7: ( 'while' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:39:9: 'while'
            {
            match("while"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    
     public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
          
            {
            match("if"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR start "STRING_OP"

    public final void mSTRING_OP() throws RecognitionException {
        try {
            int _type = STRING_OP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken id = null;

            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:306:10: ({...}? '#' ( WS )? id= IDENTIFIER )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:306:14: {...}? '#' ( WS )? id= IDENTIFIER
            {
                if (!((inDefineMode))) {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    throw new FailedPredicateException(input, "STRING_OP", "inDefineMode");
                }

                match('#');
                if (failed) {
                    return;
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:306:34: ( WS )?
                int alt1 = 2;
                int LA1_0 = input.LA(1);

                if ((LA1_0 == '\t' || (LA1_0 >= '\f' && LA1_0 <= '\r') || LA1_0 == ' ')) {
                    alt1 = 1;
                }
                switch (alt1) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:306:34: WS
                    {
                        mWS();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


                int idStart34 = getCharIndex();
                int idStartLine34 = getLine();
                int idStartCharPos34 = getCharPositionInLine();

                mIDENTIFIER();
                if (failed) {
                    return;
                }
                id = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, idStart34, getCharIndex() - 1);
                id.setLine(idStartLine34);
                id.setCharPositionInLine(idStartCharPos34);


                if (backtracking == 0) {
                    this.setText(id.getText());
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "STRING_OP"

    // $ANTLR start "DIRECTIVE"
    public final void mDIRECTIVE() throws RecognitionException {
        try {
            int _type = DIRECTIVE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            CommonToken f = null;
            CommonToken m = null;


            File sourceFile = null;
            File currentDirectory = null;
            String includeFile = null;

            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:317:3: ({...}? '#' ( WS )? ( 'ifdef' | 'ifndef' | 'if' | 'elif' | 'else' | 'endif' | 'line' | 'undef' WS | 'define' WS | 'exec_macro_expression' | ( 'include' | 'include_next' ) WS f= IDENTIFIER | ( 'include' | 'include_next' ) WS f= INCLUDE_FILE | 'warning' m= MACRO_TEXT | 'error' (m= MACRO_TEXT )? | 'pragma' m= MACRO_TEXT |) )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:317:5: {...}? '#' ( WS )? ( 'ifdef' | 'ifndef' | 'if' | 'elif' | 'else' | 'endif' | 'line' | 'undef' WS | 'define' WS | 'exec_macro_expression' | ( 'include' | 'include_next' ) WS f= IDENTIFIER | ( 'include' | 'include_next' ) WS f= INCLUDE_FILE | 'warning' m= MACRO_TEXT | 'error' (m= MACRO_TEXT )? | 'pragma' m= MACRO_TEXT |)
            {
                if (!((!inDirective && !inDefineMode))) {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    throw new FailedPredicateException(input, "DIRECTIVE", "!inDirective && !inDefineMode");
                }

                match('#');
                if (failed) {
                    return;
                }

                if (backtracking == 0) {
                    inDirective = true;
                    cpp = 'X';
                    discardSpace = false;
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:317:93: ( WS )?
                int alt2 = 2;
                int LA2_0 = input.LA(1);

                if ((LA2_0 == '\t' || (LA2_0 >= '\f' && LA2_0 <= '\r') || LA2_0 == ' ')) {
                    alt2 = 1;
                }
                switch (alt2) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:317:93: WS
                    {
                        mWS();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:318:3: ( 'ifdef' | 'ifndef' | 'if' | 'elif' | 'else' | 'endif' | 'line' | 'undef' WS | 'define' WS | 'exec_macro_expression' | ( 'include' | 'include_next' ) WS f= IDENTIFIER | ( 'include' | 'include_next' ) WS f= INCLUDE_FILE | 'warning' m= MACRO_TEXT | 'error' (m= MACRO_TEXT )? | 'pragma' m= MACRO_TEXT |)
                int alt6 = 16;
                alt6 = dfa6.predict(input);
                switch (alt6) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:318:6: 'ifdef'
                    {
                        match("ifdef");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = IF;
                            discardSpace = true;
                            this.setText("ifdef");
                        }

                    }
                    break;
                    case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:319:6: 'ifndef'
                    {
                        match("ifndef");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = IF;
                            discardSpace = true;
                            this.setText("ifndef");
                        }

                    }
                    break;
                    case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:320:6: 'if'
                    {
                        mIF();
                        if (failed) {
                            return;
                        }

                        if (backtracking == 0) {
                            _type = IF;
                            discardSpace = true;
                            this.setText("if");
                        }

                    }
                    break;
                    case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:321:6: 'elif'
                    {
                        match("elif");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = ELIF;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 5: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:322:6: 'else'
                    {
                        match("else");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = ELSE;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 6: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:323:6: 'endif'
                    {
                        match("endif");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = ENDIF;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 7: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:324:6: 'line'
                    {
                        match("line");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = LINE;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 8: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:325:6: 'undef' WS
                    {
                        match("undef");
                        if (failed) {
                            return;
                        }



                        mWS();
                        if (failed) {
                            return;
                        }


                        if (backtracking == 0) {
                            _type = UNDEF;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 9: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:326:6: 'define' WS
                    {
                        match("define");
                        if (failed) {
                            return;
                        }



                        mWS();
                        if (failed) {
                            return;
                        }


                        if (backtracking == 0) {
                            _type = DEFINE;
                            cpp = 'D';
                            discardSpace = false;
                        }

                    }
                    break;
                    case 10: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:327:6: 'exec_macro_expression'
                    {
                        match("exec_macro_expression");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = EXEC_MACRO;
                            discardSpace = true;
                        }

                    }
                    break;
                    case 11: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:328:6: ( 'include' | 'include_next' ) WS f= IDENTIFIER
                    {
                        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:328:6: ( 'include' | 'include_next' )
                        int alt3 = 2;
                        int LA3_0 = input.LA(1);

                        if ((LA3_0 == 'i')) {
                            int LA3_1 = input.LA(2);

                            if ((LA3_1 == 'n')) {
                                int LA3_2 = input.LA(3);

                                if ((LA3_2 == 'c')) {
                                    int LA3_3 = input.LA(4);

                                    if ((LA3_3 == 'l')) {
                                        int LA3_4 = input.LA(5);

                                        if ((LA3_4 == 'u')) {
                                            int LA3_5 = input.LA(6);

                                            if ((LA3_5 == 'd')) {
                                                int LA3_6 = input.LA(7);

                                                if ((LA3_6 == 'e')) {
                                                    int LA3_7 = input.LA(8);

                                                    if ((LA3_7 == '_')) {
                                                        alt3 = 2;
                                                    } else if ((LA3_7 == '\t' || (LA3_7 >= '\f' && LA3_7 <= '\r') || LA3_7 == ' ')) {
                                                        alt3 = 1;
                                                    } else {
                                                        if (backtracking > 0) {
                                                            failed = true;
                                                            return;
                                                        }
                                                        NoViableAltException nvae =
                                                                new NoViableAltException("", 3, 7, input);

                                                        throw nvae;

                                                    }
                                                } else {
                                                    if (backtracking > 0) {
                                                        failed = true;
                                                        return;
                                                    }
                                                    NoViableAltException nvae =
                                                            new NoViableAltException("", 3, 6, input);

                                                    throw nvae;

                                                }
                                            } else {
                                                if (backtracking > 0) {
                                                    failed = true;
                                                    return;
                                                }
                                                NoViableAltException nvae =
                                                        new NoViableAltException("", 3, 5, input);

                                                throw nvae;

                                            }
                                        } else {
                                            if (backtracking > 0) {
                                                failed = true;
                                                return;
                                            }
                                            NoViableAltException nvae =
                                                    new NoViableAltException("", 3, 4, input);

                                            throw nvae;

                                        }
                                    } else {
                                        if (backtracking > 0) {
                                            failed = true;
                                            return;
                                        }
                                        NoViableAltException nvae =
                                                new NoViableAltException("", 3, 3, input);

                                        throw nvae;

                                    }
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    NoViableAltException nvae =
                                            new NoViableAltException("", 3, 2, input);

                                    throw nvae;

                                }
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                NoViableAltException nvae =
                                        new NoViableAltException("", 3, 1, input);

                                throw nvae;

                            }
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 3, 0, input);

                            throw nvae;

                        }
                        switch (alt3) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:328:7: 'include'
                            {
                                match("include");
                                if (failed) {
                                    return;
                                }



                            }
                            break;
                            case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:328:17: 'include_next'
                            {
                                match("include_next");
                                if (failed) {
                                    return;
                                }



                            }
                            break;

                        }


                        mWS();
                        if (failed) {
                            return;
                        }


                        int fStart215 = getCharIndex();
                        int fStartLine215 = getLine();
                        int fStartCharPos215 = getCharPositionInLine();
                        mIDENTIFIER();
                        if (failed) {
                            return;
                        }
                        f = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fStart215, getCharIndex() - 1);
                        f.setLine(fStartLine215);
                        f.setCharPositionInLine(fStartCharPos215);


                        if (backtracking == 0) {
                            _type = INCLUDE_EXPAND;
                            this.setText(f.getText());
                            discardSpace = true;
                        }

                    }
                    break;
                    case 12: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:334:6: ( 'include' | 'include_next' ) WS f= INCLUDE_FILE
                    {
                        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:334:6: ( 'include' | 'include_next' )
                        int alt4 = 2;
                        int LA4_0 = input.LA(1);

                        if ((LA4_0 == 'i')) {
                            int LA4_1 = input.LA(2);

                            if ((LA4_1 == 'n')) {
                                int LA4_2 = input.LA(3);

                                if ((LA4_2 == 'c')) {
                                    int LA4_3 = input.LA(4);

                                    if ((LA4_3 == 'l')) {
                                        int LA4_4 = input.LA(5);

                                        if ((LA4_4 == 'u')) {
                                            int LA4_5 = input.LA(6);

                                            if ((LA4_5 == 'd')) {
                                                int LA4_6 = input.LA(7);

                                                if ((LA4_6 == 'e')) {
                                                    int LA4_7 = input.LA(8);

                                                    if ((LA4_7 == '_')) {
                                                        alt4 = 2;
                                                    } else if ((LA4_7 == '\t' || (LA4_7 >= '\f' && LA4_7 <= '\r') || LA4_7 == ' ')) {
                                                        alt4 = 1;
                                                    } else {
                                                        if (backtracking > 0) {
                                                            failed = true;
                                                            return;
                                                        }
                                                        NoViableAltException nvae =
                                                                new NoViableAltException("", 4, 7, input);

                                                        throw nvae;

                                                    }
                                                } else {
                                                    if (backtracking > 0) {
                                                        failed = true;
                                                        return;
                                                    }
                                                    NoViableAltException nvae =
                                                            new NoViableAltException("", 4, 6, input);

                                                    throw nvae;

                                                }
                                            } else {
                                                if (backtracking > 0) {
                                                    failed = true;
                                                    return;
                                                }
                                                NoViableAltException nvae =
                                                        new NoViableAltException("", 4, 5, input);

                                                throw nvae;

                                            }
                                        } else {
                                            if (backtracking > 0) {
                                                failed = true;
                                                return;
                                            }
                                            NoViableAltException nvae =
                                                    new NoViableAltException("", 4, 4, input);

                                            throw nvae;

                                        }
                                    } else {
                                        if (backtracking > 0) {
                                            failed = true;
                                            return;
                                        }
                                        NoViableAltException nvae =
                                                new NoViableAltException("", 4, 3, input);

                                        throw nvae;

                                    }
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    NoViableAltException nvae =
                                            new NoViableAltException("", 4, 2, input);

                                    throw nvae;

                                }
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                NoViableAltException nvae =
                                        new NoViableAltException("", 4, 1, input);

                                throw nvae;

                            }
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 4, 0, input);

                            throw nvae;

                        }
                        switch (alt4) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:334:7: 'include'
                            {
                                match("include");
                                if (failed) {
                                    return;
                                }



                            }
                            break;
                            case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:334:17: 'include_next'
                            {
                                match("include_next");
                                if (failed) {
                                    return;
                                }



                            }
                            break;

                        }


                        mWS();
                        if (failed) {
                            return;
                        }


                        int fStart239 = getCharIndex();
                        int fStartLine239 = getLine();
                        int fStartCharPos239 = getCharPositionInLine();
                        mINCLUDE_FILE();
                        if (failed) {
                            return;
                        }
                        f = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, fStart239, getCharIndex() - 1);
                        f.setLine(fStartLine239);
                        f.setCharPositionInLine(fStartCharPos239);


                        if (backtracking == 0) {
                            includeFile = f.getText();

                            if (includeFile.startsWith("<")) {
                                includeFile = includeFile.substring(1, includeFile.length() - 1);

                                if (!includeFile.startsWith("/")) {
                                    for (int i = 0; i < includeSearchPaths.length; ++i) {
                                        try {
                                            String path = new File(includeSearchPaths[i]).getCanonicalPath();
                                            File incFile = new File(path, includeFile);
                                            if (incFile.exists()) {
                                                includeFile = incFile.getCanonicalPath();
                                            }
                                        } catch (Exception e) {;
                                        }
                                    }
                                }
                            } else {
                                includeFile = includeFile.substring(1, includeFile.length() - 1);
                            }
                            _type = INCLUDE;
                            discardSpace = true;
                            this.setText(includeFile);
                        }

                    }
                    break;
                    case 13: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:383:6: 'warning' m= MACRO_TEXT
                    {
                        match("warning");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = WARNING;
                        }

                        int mStart261 = getCharIndex();
                        int mStartLine261 = getLine();
                        int mStartCharPos261 = getCharPositionInLine();
                        mMACRO_TEXT();
                        if (failed) {
                            return;
                        }
                        m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart261, getCharIndex() - 1);
                        m.setLine(mStartLine261);
                        m.setCharPositionInLine(mStartCharPos261);


                        if (backtracking == 0) {
                            this.setText(m.getText());
                        }

                    }
                    break;
                    case 14: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:384:6: 'error' (m= MACRO_TEXT )?
                    {
                        match("error");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = ERROR;
                        }

                        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:384:38: (m= MACRO_TEXT )?
                        int alt5 = 2;
                        int LA5_0 = input.LA(1);

                        if (((LA5_0 >= '\u0000' && LA5_0 <= '\t') || (LA5_0 >= '\u000B' && LA5_0 <= '\uFFFF'))) {
                            alt5 = 1;
                        }
                        switch (alt5) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:384:39: m= MACRO_TEXT
                            {
                                int mStart282 = getCharIndex();
                                int mStartLine282 = getLine();
                                int mStartCharPos282 = getCharPositionInLine();
                                mMACRO_TEXT();
                                if (failed) {
                                    return;
                                }
                                m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart282, getCharIndex() - 1);
                                m.setLine(mStartLine282);
                                m.setCharPositionInLine(mStartCharPos282);


                                if (backtracking == 0) {
                                    this.setText(m.getText());
                                }

                            }
                            break;

                        }


                    }
                    break;
                    case 15: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:385:6: 'pragma' m= MACRO_TEXT
                    {
                        match("pragma");
                        if (failed) {
                            return;
                        }



                        if (backtracking == 0) {
                            _type = PRAGMA;
                        }

                        int mStart302 = getCharIndex();
                        int mStartLine302 = getLine();
                        int mStartCharPos302 = getCharPositionInLine();
                        mMACRO_TEXT();
                        if (failed) {
                            return;
                        }
                        m = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, mStart302, getCharIndex() - 1);
                        m.setLine(mStartLine302);
                        m.setCharPositionInLine(mStartCharPos302);


                        if (backtracking == 0) {
                            this.setText(m.getText());
                        }

                    }
                    break;
                    case 16: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:387:4: 
                    {
                    }
                    break;

                }


            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DIRECTIVE"

    // $ANTLR start "MACRO_TEXT"
    public final void mMACRO_TEXT() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:9: ( ( ( '/*' )=> '/*' ( options {greedy=false; } : . )* '*/' | ( '\\\\\\n' )=> ( '\\\\\\n' ) | ( '\\\\\\r\\n' )=> ( '\\\\\\n' ) |~ '\\n' )+ )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:17: ( ( '/*' )=> '/*' ( options {greedy=false; } : . )* '*/' | ( '\\\\\\n' )=> ( '\\\\\\n' ) | ( '\\\\\\r\\n' )=> ( '\\\\\\n' ) |~ '\\n' )+
            {
                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:17: ( ( '/*' )=> '/*' ( options {greedy=false; } : . )* '*/' | ( '\\\\\\n' )=> ( '\\\\\\n' ) | ( '\\\\\\r\\n' )=> ( '\\\\\\n' ) |~ '\\n' )+
                int cnt8 = 0;
                loop8:
                do {
                    int alt8 = 5;
                    alt8 = dfa8.predict(input);
                    switch (alt8) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:21: ( '/*' )=> '/*' ( options {greedy=false; } : . )* '*/'
                        {
                            match("/*");
                            if (failed) {
                                return;
                            }



                            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:38: ( options {greedy=false; } : . )*
                            loop7:
                            do {
                                int alt7 = 2;
                                int LA7_0 = input.LA(1);

                                if ((LA7_0 == '*')) {
                                    int LA7_1 = input.LA(2);

                                    if ((LA7_1 == '/')) {
                                        alt7 = 2;
                                    } else if (((LA7_1 >= '\u0000' && LA7_1 <= '.') || (LA7_1 >= '0' && LA7_1 <= '\uFFFF'))) {
                                        alt7 = 1;
                                    }


                                } else if (((LA7_0 >= '\u0000' && LA7_0 <= ')') || (LA7_0 >= '+' && LA7_0 <= '\uFFFF'))) {
                                    alt7 = 1;
                                }


                                switch (alt7) {
                                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:64: .
                                    {
                                        matchAny();
                                        if (failed) {
                                            return;
                                        }

                                    }
                                    break;

                                    default:
                                        break loop7;
                                }
                            } while (true);


                            match("*/");
                            if (failed) {
                                return;
                            }



                        }
                        break;
                        case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:392:17: ( '\\\\\\n' )=> ( '\\\\\\n' )
                        {
                            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:392:29: ( '\\\\\\n' )
                            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:392:30: '\\\\\\n'
                            {
                                match("\\\n");
                                if (failed) {
                                    return;
                                }



                            }


                        }
                        break;
                        case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:393:17: ( '\\\\\\r\\n' )=> ( '\\\\\\n' )
                        {
                            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:393:31: ( '\\\\\\n' )
                            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:393:32: '\\\\\\n'
                            {
                                match("\\\n");
                                if (failed) {
                                    return;
                                }



                            }


                        }
                        break;
                        case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:394:17: ~ '\\n'
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt8 >= 1) {
                                break loop8;
                            }
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(8, input);
                            throw eee;
                    }
                    cnt8++;
                } while (true);


            }


        } finally {
        }
    }
    // $ANTLR end "MACRO_TEXT"

    // $ANTLR start "SIZEOF"
    public final void mSIZEOF() throws RecognitionException {
        try {
            int _type = SIZEOF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:398:8: ( 'sizeof' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:398:10: 'sizeof'
            {
                match("sizeof");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SIZEOF"

    // $ANTLR start "DEFINED"
    public final void mDEFINED() throws RecognitionException {
        try {
            int _type = DEFINED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:401:9: ( 'defined' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:401:11: 'defined'
            {
                match("defined");
                if (failed) {
                    return;
                }



                if (backtracking == 0) {
                    if (!inDirective) {
                        _type = CKEYWORD;
                    }
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DEFINED"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:409:3: ( LETTER ( LETTER | '0' .. '9' )* )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:409:5: LETTER ( LETTER | '0' .. '9' )*
            {
                mLETTER();
                if (failed) {
                    return;
                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:409:12: ( LETTER | '0' .. '9' )*
                loop9:
                do {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);

                    if ((LA9_0 == '$' || (LA9_0 >= '0' && LA9_0 <= '9') || (LA9_0 >= 'A' && LA9_0 <= 'Z') || LA9_0 == '_' || (LA9_0 >= 'a' && LA9_0 <= 'z'))) {
                        alt9 = 1;
                    }


                    switch (alt9) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if (input.LA(1) == '$' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop9;
                    }
                } while (true);


                if (backtracking == 0) {
                    if (inDirective == true) {
                        if (cpp == 'D') {
                            if (input.LA(1) != '(') {
                                inDefineMode = true;
                                inDirective = false;
                                cpp = 'X';
                            } else {
                                cpp = '(';
                            }
                        }
                    }
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:432:8: ( '$' | 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
            {
                if (input.LA(1) == '$' || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                    failed = false;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }


        } finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "STRINGIFICATION"
    public final void mSTRINGIFICATION() throws RecognitionException {
        try {
            int _type = STRINGIFICATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:439:17: ( '#_#_' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:439:19: '#_#_'
            {
                match("#_#_");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "STRINGIFICATION"

    // $ANTLR start "SHARPSHARP"
    public final void mSHARPSHARP() throws RecognitionException {
        try {
            int _type = SHARPSHARP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:442:13: ( '##' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:442:15: '##'
            {
                match("##");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SHARPSHARP"

    // $ANTLR start "ASSIGNEQUAL"
    public final void mASSIGNEQUAL() throws RecognitionException {
        try {
            int _type = ASSIGNEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:444:14: ( '=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:444:16: '='
            {
                match('=');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "ASSIGNEQUAL"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:446:9: ( ':' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:446:11: ':'
            {
                match(':');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:448:9: ( ',' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:448:11: ','
            {
                match(',');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "QUESTIONMARK"
    public final void mQUESTIONMARK() throws RecognitionException {
        try {
            int _type = QUESTIONMARK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:450:14: ( '?' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:450:16: '?'
            {
                match('?');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "QUESTIONMARK"

    // $ANTLR start "SEMICOLON"
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:452:12: ( ';' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:452:14: ';'
            {
                match(';');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SEMICOLON"

    // $ANTLR start "POINTERTO"
    public final void mPOINTERTO() throws RecognitionException {
        try {
            int _type = POINTERTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:454:12: ( '->' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:454:14: '->'
            {
                match("->");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "POINTERTO"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:456:10: ( '(' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:456:12: '('
            {
                match('(');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:458:8: ( ')' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:458:10: ')'
            {
                match(')');
                if (failed) {
                    return;
                }

                if (backtracking == 0) {
                    if (cpp == '(') {
                        if (input.LA(1) != '\n') {
                            inDefineMode = true;
                            inDirective = false;
                        }
                        cpp = 'X';
                    }
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LSQUARE"
    public final void mLSQUARE() throws RecognitionException {
        try {
            int _type = LSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:472:11: ( '[' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:472:13: '['
            {
                match('[');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LSQUARE"

    // $ANTLR start "RSQUARE"
    public final void mRSQUARE() throws RecognitionException {
        try {
            int _type = RSQUARE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:474:11: ( ']' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:474:13: ']'
            {
                match(']');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RSQUARE"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:476:10: ( '{' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:476:12: '{'
            {
                match('{');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:478:10: ( '}' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:478:12: '}'
            {
                match('}');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "EQUAL"
    public final void mEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = EQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:480:9: ( '==' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:480:11: '=='
            {
                match("==");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "NOTEQUAL"
    public final void mINEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = INEQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:482:11: ( '!=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:482:13: '!='
            {
                match("!=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "LESSTHANOREQUALTO"
    public final void mLESS_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = LESS_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:485:5: ( '<=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:485:7: '<='
            {
                match("<=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LESSTHANOREQUALTO"

    // $ANTLR start "LESSTHAN"
    public final void mLOWER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = LOWER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:487:12: ( '<' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:487:14: '<'
            {
                match('<');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LESSTHAN"

    // $ANTLR start "GREATERTHANOREQUALTO"
    public final void mGREATER_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:490:5: ( '>=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:490:7: '>='
            {
                match(">=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "GREATERTHANOREQUALTO"

    // $ANTLR start "GREATERTHAN"
    public final void mGREATER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:492:14: ( '>' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:492:16: '>'
            {
                match('>');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "GREATERTHAN"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:494:10: ( '/' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:494:12: '/'
            {
                match('/');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "DIVIDEEQUAL"
    public final void mDIVIDEEQUAL() throws RecognitionException {
        try {
            int _type = DIVIDEEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:496:14: ( '/=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:496:16: '/='
            {
                match("/=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DIVIDEEQUAL"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:498:8: ( '+' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:498:10: '+'
            {
                match('+');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "PLUSEQUAL"
    public final void mPLUSEQUAL() throws RecognitionException {
        try {
            int _type = PLUSEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:500:12: ( '+=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:500:14: '+='
            {
                match("+=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "PLUSEQUAL"

    // $ANTLR start "PLUSPLUS"
    public final void mPLUSPLUS() throws RecognitionException {
        try {
            int _type = PLUSPLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:502:11: ( '++' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:502:13: '++'
            {
                match("++");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "PLUSPLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:504:9: ( '-' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:504:11: '-'
            {
                match('-');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MINUSEQUAL"
    public final void mMINUSEQUAL() throws RecognitionException {
        try {
            int _type = MINUSEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:506:13: ( '-=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:506:15: '-='
            {
                match("-=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "MINUSEQUAL"

    // $ANTLR start "MINUSMINUS"
    public final void mMINUSMINUS() throws RecognitionException {
        try {
            int _type = MINUSMINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:508:13: ( '--' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:508:15: '--'
            {
                match("--");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "MINUSMINUS"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:510:8: ( '*' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:510:10: '*'
            {
                match('*');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "TIMESEQUAL"
    public final void mTIMESEQUAL() throws RecognitionException {
        try {
            int _type = TIMESEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:512:13: ( '*=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:512:15: '*='
            {
                match("*=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "TIMESEQUAL"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:514:8: ( '%' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:514:10: '%'
            {
                match('%');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "MODEQUAL"
    public final void mMODEQUAL() throws RecognitionException {
        try {
            int _type = MODEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:516:11: ( '%=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:516:13: '%='
            {
                match("%=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "MODEQUAL"

    // $ANTLR start "SHIFTRIGHT"
    public final void mSHIFTRIGHT() throws RecognitionException {
        try {
            int _type = SHIFTRIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:518:13: ( '>>' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:518:15: '>>'
            {
                match(">>");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SHIFTRIGHT"

    // $ANTLR start "SHIFTRIGHTEQUAL"
    public final void mSHIFTRIGHTEQUAL() throws RecognitionException {
        try {
            int _type = SHIFTRIGHTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:520:17: ( '>>=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:520:19: '>>='
            {
                match(">>=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SHIFTRIGHTEQUAL"

    // $ANTLR start "SHIFTLEFT"
    public final void mSHIFTLEFT() throws RecognitionException {
        try {
            int _type = SHIFTLEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:522:12: ( '<<' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:522:14: '<<'
            {
                match("<<");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SHIFTLEFT"

    // $ANTLR start "SHIFTLEFTEQUAL"
    public final void mSHIFTLEFTEQUAL() throws RecognitionException {
        try {
            int _type = SHIFTLEFTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:524:16: ( '<<=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:524:18: '<<='
            {
                match("<<=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SHIFTLEFTEQUAL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:526:8: ( '&&' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:526:10: '&&'
            {
                match("&&");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "NOT"
    public final void mNEGATION_OPERATOR() throws RecognitionException {
        try {
            int _type = NEGATION_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:528:8: ( '!' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:528:10: '!'
            {
                match('!');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:530:7: ( '||' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:530:9: '||'
            {
                match("||");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:532:12: ( '&' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:532:14: '&'
            {
                match('&');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "BITWISEANDEQUAL"
    public final void mBITWISEANDEQUAL() throws RecognitionException {
        try {
            int _type = BITWISEANDEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:534:17: ( '&=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:534:19: '&='
            {
                match("&=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "BITWISEANDEQUAL"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:536:9: ( '~' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:536:11: '~'
            {
                match('~');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "BITWISEOR"
    public final void mBITWISEOR() throws RecognitionException {
        try {
            int _type = BITWISEOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:538:12: ( '|' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:538:14: '|'
            {
                match('|');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "BITWISEOR"

    // $ANTLR start "BITWISEOREQUAL"
    public final void mBITWISEOREQUAL() throws RecognitionException {
        try {
            int _type = BITWISEOREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:540:16: ( '|=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:540:18: '|='
            {
                match("|=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "BITWISEOREQUAL"

    // $ANTLR start "BITWISEXOR"
    public final void mBITWISEXOR() throws RecognitionException {
        try {
            int _type = BITWISEXOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:542:13: ( '^' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:542:15: '^'
            {
                match('^');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "BITWISEXOR"

    // $ANTLR start "BITWISEXOREQUAL"
    public final void mBITWISEXOREQUAL() throws RecognitionException {
        try {
            int _type = BITWISEXOREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:544:17: ( '^=' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:544:19: '^='
            {
                match("^=");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "BITWISEXOREQUAL"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:546:8: ( '.' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:546:10: '.'
            {
                match('.');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "POINTERTOMBR"
    public final void mPOINTERTOMBR() throws RecognitionException {
        try {
            int _type = POINTERTOMBR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:548:14: ( '->*' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:548:16: '->*'
            {
                match("->*");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "POINTERTOMBR"

    // $ANTLR start "DOTMBR"
    public final void mDOTMBR() throws RecognitionException {
        try {
            int _type = DOTMBR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:550:10: ( '.*' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:550:12: '.*'
            {
                match(".*");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DOTMBR"

    // $ANTLR start "SCOPE"
    public final void mSCOPE() throws RecognitionException {
        try {
            int _type = SCOPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:552:9: ( '::' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:552:11: '::'
            {
                match("::");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "SCOPE"

    // $ANTLR start "ELLIPSIS"
    public final void mELLIPSIS() throws RecognitionException {
        try {
            int _type = ELLIPSIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:554:11: ( '...' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:554:13: '...'
            {
                match("...");
                if (failed) {
                    return;
                }



            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "ELLIPSIS"

    // $ANTLR start "INCLUDE_FILE"
    public final void mINCLUDE_FILE() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:559:3: ( '<' ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+ '>' | '\"' ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+ '\"' )
            int alt12 = 2;
            int LA12_0 = input.LA(1);

            if ((LA12_0 == '<')) {
                alt12 = 1;
            } else if ((LA12_0 == '\"')) {
                alt12 = 2;
            } else {
                if (backtracking > 0) {
                    failed = true;
                    return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:559:5: '<' ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+ '>'
                {
                    match('<');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:559:9: ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+
                    int cnt10 = 0;
                    loop10:
                    do {
                        int alt10 = 2;
                        int LA10_0 = input.LA(1);

                        if (((LA10_0 >= ' ' && LA10_0 <= '!') || (LA10_0 >= '#' && LA10_0 <= ';') || LA10_0 == '=' || (LA10_0 >= '?' && LA10_0 <= '[') || (LA10_0 >= ']' && LA10_0 <= '\u00FF'))) {
                            alt10 = 1;
                        }


                        switch (alt10) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= ' ' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= ';') || input.LA(1) == '=' || (input.LA(1) >= '?' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\u00FF')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt10 >= 1) {
                                    break loop10;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(10, input);
                                throw eee;
                        }
                        cnt10++;
                    } while (true);


                    match('>');
                    if (failed) {
                        return;
                    }

                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:560:11: '\"' ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+ '\"'
                {
                    match('\"');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:560:15: ( ' ' | '!' | '#' .. ';' | '=' | '?' .. '[' | ']' .. '\\u00FF' )+
                    int cnt11 = 0;
                    loop11:
                    do {
                        int alt11 = 2;
                        int LA11_0 = input.LA(1);

                        if (((LA11_0 >= ' ' && LA11_0 <= '!') || (LA11_0 >= '#' && LA11_0 <= ';') || LA11_0 == '=' || (LA11_0 >= '?' && LA11_0 <= '[') || (LA11_0 >= ']' && LA11_0 <= '\u00FF'))) {
                            alt11 = 1;
                        }


                        switch (alt11) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= ' ' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= ';') || input.LA(1) == '=' || (input.LA(1) >= '?' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\u00FF')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt11 >= 1) {
                                    break loop11;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    match('\"');
                    if (failed) {
                        return;
                    }

                }
                break;

            }

        } finally {
        }
    }
    // $ANTLR end "INCLUDE_FILE"

    // $ANTLR start "CHARACTER_LITERAL"
    public final void mCHARACTER_LITERAL() throws RecognitionException {
        try {
            int _type = CHARACTER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:3: ( ( 'L' )? '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\'' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:5: ( 'L' )? '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\''
            {
                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:5: ( 'L' )?
                int alt13 = 2;
                int LA13_0 = input.LA(1);

                if ((LA13_0 == 'L')) {
                    alt13 = 1;
                }
                switch (alt13) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:6: 'L'
                    {
                        match('L');
                        if (failed) {
                            return;
                        }

                    }
                    break;

                }


                match('\'');
                if (failed) {
                    return;
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:17: ( EscapeSequence |~ ( '\\'' | '\\\\' ) )
                int alt14 = 2;
                int LA14_0 = input.LA(1);

                if ((LA14_0 == '\\')) {
                    alt14 = 1;
                } else if (((LA14_0 >= '\u0000' && LA14_0 <= '&') || (LA14_0 >= '(' && LA14_0 <= '[') || (LA14_0 >= ']' && LA14_0 <= '\uFFFF'))) {
                    alt14 = 2;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                    throw nvae;

                }
                switch (alt14) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:19: EscapeSequence
                    {
                        mEscapeSequence();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:563:36: ~ ( '\\'' | '\\\\' )
                    {
                        if ((input.LA(1) >= '\u0000' && input.LA(1) <= '&') || (input.LA(1) >= '(' && input.LA(1) <= '[') || (input.LA(1) >= ']' && input.LA(1) <= '\uFFFF')) {
                            input.consume();
                            failed = false;
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }


                    }
                    break;

                }


                match('\'');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "CHARACTER_LITERAL"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:568:3: ( '\"' ( EscapeSequence |~ ( '\"' ) )* '\"' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:568:5: '\"' ( EscapeSequence |~ ( '\"' ) )* '\"'
            {
                match('\"');
                if (failed) {
                    return;
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:568:9: ( EscapeSequence |~ ( '\"' ) )*
                loop15:
                do {
                    int alt15 = 3;
                    int LA15_0 = input.LA(1);

                    if ((LA15_0 == '\\')) {
                        int LA15_2 = input.LA(2);

                        if ((LA15_2 == '\"')) {
                            int LA15_4 = input.LA(3);

                            if (((LA15_4 >= '\u0000' && LA15_4 <= '\uFFFF'))) {
                                alt15 = 1;
                            } else {
                                alt15 = 2;
                            }


                        } else if ((LA15_2 == 'x')) {
                            int LA15_5 = input.LA(3);

                            if (((LA15_5 >= '0' && LA15_5 <= '9') || (LA15_5 >= 'A' && LA15_5 <= 'F') || (LA15_5 >= 'a' && LA15_5 <= 'f'))) {
                                alt15 = 1;
                            } else if (((LA15_5 >= '\u0000' && LA15_5 <= '/') || (LA15_5 >= ':' && LA15_5 <= '@') || (LA15_5 >= 'G' && LA15_5 <= '`') || (LA15_5 >= 'g' && LA15_5 <= '\uFFFF'))) {
                                alt15 = 2;
                            }


                        } else if (((LA15_2 >= '0' && LA15_2 <= '3'))) {
                            alt15 = 1;
                        } else if (((LA15_2 >= '4' && LA15_2 <= '7'))) {
                            alt15 = 1;
                        } else if ((LA15_2 == '\\')) {
                            alt15 = 1;
                        } else if ((LA15_2 == '\'' || LA15_2 == 'b' || LA15_2 == 'f' || LA15_2 == 'n' || LA15_2 == 'r' || LA15_2 == 't' || LA15_2 == 'v')) {
                            alt15 = 1;
                        } else if (((LA15_2 >= '\u0000' && LA15_2 <= '!') || (LA15_2 >= '#' && LA15_2 <= '&') || (LA15_2 >= '(' && LA15_2 <= '/') || (LA15_2 >= '8' && LA15_2 <= '[') || (LA15_2 >= ']' && LA15_2 <= 'a') || (LA15_2 >= 'c' && LA15_2 <= 'e') || (LA15_2 >= 'g' && LA15_2 <= 'm') || (LA15_2 >= 'o' && LA15_2 <= 'q') || LA15_2 == 's' || LA15_2 == 'u' || LA15_2 == 'w' || (LA15_2 >= 'y' && LA15_2 <= '\uFFFF'))) {
                            alt15 = 2;
                        }


                    } else if (((LA15_0 >= '\u0000' && LA15_0 <= '!') || (LA15_0 >= '#' && LA15_0 <= '[') || (LA15_0 >= ']' && LA15_0 <= '\uFFFF'))) {
                        alt15 = 2;
                    }


                    switch (alt15) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:568:10: EscapeSequence
                        {
                            mEscapeSequence();
                            if (failed) {
                                return;
                            }


                        }
                        break;
                        case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:568:27: ~ ( '\"' )
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= '\uFFFF')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop15;
                    }
                } while (true);


                match('\"');
                if (failed) {
                    return;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "HEX_LITERAL"
    public final void mHEX_LITERAL() throws RecognitionException {
        try {
            int _type = HEX_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:572:3: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:572:5: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
                match('0');
                if (failed) {
                    return;
                }

                if (input.LA(1) == 'X' || input.LA(1) == 'x') {
                    input.consume();
                    failed = false;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:572:19: ( HexDigit )+
                int cnt16 = 0;
                loop16:
                do {
                    int alt16 = 2;
                    int LA16_0 = input.LA(1);

                    if (((LA16_0 >= '0' && LA16_0 <= '9') || (LA16_0 >= 'A' && LA16_0 <= 'F') || (LA16_0 >= 'a' && LA16_0 <= 'f'))) {
                        alt16 = 1;
                    }


                    switch (alt16) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt16 >= 1) {
                                break loop16;
                            }
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(16, input);
                            throw eee;
                    }
                    cnt16++;
                } while (true);


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:572:29: ( IntegerTypeSuffix )?
                int alt17 = 2;
                int LA17_0 = input.LA(1);

                if ((LA17_0 == 'L' || LA17_0 == 'U' || LA17_0 == 'l' || LA17_0 == 'u')) {
                    alt17 = 1;
                }
                switch (alt17) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:572:29: IntegerTypeSuffix
                    {
                        mIntegerTypeSuffix();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "HEX_LITERAL"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:3: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:5: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:5: ( '0' | '1' .. '9' ( '0' .. '9' )* )
                int alt19 = 2;
                int LA19_0 = input.LA(1);

                if ((LA19_0 == '0')) {
                    alt19 = 1;
                } else if (((LA19_0 >= '1' && LA19_0 <= '9'))) {
                    alt19 = 2;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 19, 0, input);

                    throw nvae;

                }
                switch (alt19) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:6: '0'
                    {
                        match('0');
                        if (failed) {
                            return;
                        }

                    }
                    break;
                    case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:12: '1' .. '9' ( '0' .. '9' )*
                    {
                        matchRange('1', '9');
                        if (failed) {
                            return;
                        }

                        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:21: ( '0' .. '9' )*
                        loop18:
                        do {
                            int alt18 = 2;
                            int LA18_0 = input.LA(1);

                            if (((LA18_0 >= '0' && LA18_0 <= '9'))) {
                                alt18 = 1;
                            }


                            switch (alt18) {
                                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                                {
                                    if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                        input.consume();
                                        failed = false;
                                    } else {
                                        if (backtracking > 0) {
                                            failed = true;
                                            return;
                                        }
                                        MismatchedSetException mse = new MismatchedSetException(null, input);
                                        recover(mse);
                                        throw mse;
                                    }


                                }
                                break;

                                default:
                                    break loop18;
                            }
                        } while (true);


                    }
                    break;

                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:32: ( IntegerTypeSuffix )?
                int alt20 = 2;
                int LA20_0 = input.LA(1);

                if ((LA20_0 == 'L' || LA20_0 == 'U' || LA20_0 == 'l' || LA20_0 == 'u')) {
                    alt20 = 1;
                }
                switch (alt20) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:576:32: IntegerTypeSuffix
                    {
                        mIntegerTypeSuffix();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "OCTAL_LITERAL"
    public final void mOCTAL_LITERAL() throws RecognitionException {
        try {
            int _type = OCTAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:580:3: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:580:5: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
                match('0');
                if (failed) {
                    return;
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:580:9: ( '0' .. '7' )+
                int cnt21 = 0;
                loop21:
                do {
                    int alt21 = 2;
                    int LA21_0 = input.LA(1);

                    if (((LA21_0 >= '0' && LA21_0 <= '7'))) {
                        alt21 = 1;
                    }


                    switch (alt21) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt21 >= 1) {
                                break loop21;
                            }
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(21, input);
                            throw eee;
                    }
                    cnt21++;
                } while (true);


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:580:21: ( IntegerTypeSuffix )?
                int alt22 = 2;
                int LA22_0 = input.LA(1);

                if ((LA22_0 == 'L' || LA22_0 == 'U' || LA22_0 == 'l' || LA22_0 == 'u')) {
                    alt22 = 1;
                }
                switch (alt22) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:580:21: IntegerTypeSuffix
                    {
                        mIntegerTypeSuffix();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:584:3: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
            {
                if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                    input.consume();
                    failed = false;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }


        } finally {
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:3: ( 'u' | 'ul' | 'U' | 'UL' | 'l' | 'L' )
            int alt23 = 6;
            switch (input.LA(1)) {
                case 'u': {
                    int LA23_1 = input.LA(2);

                    if ((LA23_1 == 'l')) {
                        alt23 = 2;
                    } else {
                        alt23 = 1;
                    }
                }
                break;
                case 'U': {
                    int LA23_2 = input.LA(2);

                    if ((LA23_2 == 'L')) {
                        alt23 = 4;
                    } else {
                        alt23 = 3;
                    }
                }
                break;
                case 'l': {
                    alt23 = 5;
                }
                break;
                case 'L': {
                    alt23 = 6;
                }
                break;
                default:
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 23, 0, input);

                    throw nvae;

            }

            switch (alt23) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:5: 'u'
                {
                    match('u');
                    if (failed) {
                        return;
                    }

                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:11: 'ul'
                {
                    match("ul");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:18: 'U'
                {
                    match('U');
                    if (failed) {
                        return;
                    }

                }
                break;
                case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:24: 'UL'
                {
                    match("UL");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 5: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:31: 'l'
                {
                    match('l');
                    if (failed) {
                        return;
                    }

                }
                break;
                case 6: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:588:37: 'L'
                {
                    match('L');
                    if (failed) {
                        return;
                    }

                }
                break;

            }

        } finally {
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "FLOATING_POINT_LITERAL"
    public final void mFLOATING_POINT_LITERAL() throws RecognitionException {
        try {
            int _type = FLOATING_POINT_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:3: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix )
            int alt34 = 4;
            alt34 = dfa34.predict(input);
            switch (alt34) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                {
                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:5: ( '0' .. '9' )+
                    int cnt24 = 0;
                    loop24:
                    do {
                        int alt24 = 2;
                        int LA24_0 = input.LA(1);

                        if (((LA24_0 >= '0' && LA24_0 <= '9'))) {
                            alt24 = 1;
                        }


                        switch (alt24) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt24 >= 1) {
                                    break loop24;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);


                    match('.');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:21: ( '0' .. '9' )*
                    loop25:
                    do {
                        int alt25 = 2;
                        int LA25_0 = input.LA(1);

                        if (((LA25_0 >= '0' && LA25_0 <= '9'))) {
                            alt25 = 1;
                        }


                        switch (alt25) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                break loop25;
                        }
                    } while (true);


                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:33: ( Exponent )?
                    int alt26 = 2;
                    int LA26_0 = input.LA(1);

                    if ((LA26_0 == 'E' || LA26_0 == 'e')) {
                        alt26 = 1;
                    }
                    switch (alt26) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:33: Exponent
                        {
                            mExponent();
                            if (failed) {
                                return;
                            }


                        }
                        break;

                    }


                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:592:43: ( FloatTypeSuffix )?
                    int alt27 = 2;
                    int LA27_0 = input.LA(1);

                    if ((LA27_0 == 'D' || LA27_0 == 'F' || LA27_0 == 'd' || LA27_0 == 'f')) {
                        alt27 = 1;
                    }
                    switch (alt27) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if (input.LA(1) == 'D' || input.LA(1) == 'F' || input.LA(1) == 'd' || input.LA(1) == 'f') {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                    }


                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:593:5: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                {
                    match('.');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:593:9: ( '0' .. '9' )+
                    int cnt28 = 0;
                    loop28:
                    do {
                        int alt28 = 2;
                        int LA28_0 = input.LA(1);

                        if (((LA28_0 >= '0' && LA28_0 <= '9'))) {
                            alt28 = 1;
                        }


                        switch (alt28) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt28 >= 1) {
                                    break loop28;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);


                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:593:21: ( Exponent )?
                    int alt29 = 2;
                    int LA29_0 = input.LA(1);

                    if ((LA29_0 == 'E' || LA29_0 == 'e')) {
                        alt29 = 1;
                    }
                    switch (alt29) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:593:21: Exponent
                        {
                            mExponent();
                            if (failed) {
                                return;
                            }


                        }
                        break;

                    }


                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:593:31: ( FloatTypeSuffix )?
                    int alt30 = 2;
                    int LA30_0 = input.LA(1);

                    if ((LA30_0 == 'D' || LA30_0 == 'F' || LA30_0 == 'd' || LA30_0 == 'f')) {
                        alt30 = 1;
                    }
                    switch (alt30) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if (input.LA(1) == 'D' || input.LA(1) == 'F' || input.LA(1) == 'd' || input.LA(1) == 'f') {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                    }


                }
                break;
                case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:594:5: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                {
                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:594:5: ( '0' .. '9' )+
                    int cnt31 = 0;
                    loop31:
                    do {
                        int alt31 = 2;
                        int LA31_0 = input.LA(1);

                        if (((LA31_0 >= '0' && LA31_0 <= '9'))) {
                            alt31 = 1;
                        }


                        switch (alt31) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt31 >= 1) {
                                    break loop31;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(31, input);
                                throw eee;
                        }
                        cnt31++;
                    } while (true);


                    mExponent();
                    if (failed) {
                        return;
                    }


                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:594:26: ( FloatTypeSuffix )?
                    int alt32 = 2;
                    int LA32_0 = input.LA(1);

                    if ((LA32_0 == 'D' || LA32_0 == 'F' || LA32_0 == 'd' || LA32_0 == 'f')) {
                        alt32 = 1;
                    }
                    switch (alt32) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if (input.LA(1) == 'D' || input.LA(1) == 'F' || input.LA(1) == 'd' || input.LA(1) == 'f') {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                    }


                }
                break;
                case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:595:5: ( '0' .. '9' )+ FloatTypeSuffix
                {
                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:595:5: ( '0' .. '9' )+
                    int cnt33 = 0;
                    loop33:
                    do {
                        int alt33 = 2;
                        int LA33_0 = input.LA(1);

                        if (((LA33_0 >= '0' && LA33_0 <= '9'))) {
                            alt33 = 1;
                        }


                        switch (alt33) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt33 >= 1) {
                                    break loop33;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(33, input);
                                throw eee;
                        }
                        cnt33++;
                    } while (true);


                    mFloatTypeSuffix();
                    if (failed) {
                        return;
                    }


                }
                break;

            }
            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "FLOATING_POINT_LITERAL"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:599:3: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:599:5: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
                if (input.LA(1) == 'E' || input.LA(1) == 'e') {
                    input.consume();
                    failed = false;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:599:15: ( '+' | '-' )?
                int alt35 = 2;
                int LA35_0 = input.LA(1);

                if ((LA35_0 == '+' || LA35_0 == '-')) {
                    alt35 = 1;
                }
                switch (alt35) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                    {
                        if (input.LA(1) == '+' || input.LA(1) == '-') {
                            input.consume();
                            failed = false;
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }


                    }
                    break;

                }


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:599:26: ( '0' .. '9' )+
                int cnt36 = 0;
                loop36:
                do {
                    int alt36 = 2;
                    int LA36_0 = input.LA(1);

                    if (((LA36_0 >= '0' && LA36_0 <= '9'))) {
                        alt36 = 1;
                    }


                    switch (alt36) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt36 >= 1) {
                                break loop36;
                            }
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(36, input);
                            throw eee;
                    }
                    cnt36++;
                } while (true);


            }


        } finally {
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "FloatTypeSuffix"
    public final void mFloatTypeSuffix() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:603:3: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
            {
                if (input.LA(1) == 'D' || input.LA(1) == 'F' || input.LA(1) == 'd' || input.LA(1) == 'f') {
                    input.consume();
                    failed = false;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }


        } finally {
        }
    }
    // $ANTLR end "FloatTypeSuffix"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:607:3: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'v' | '\\\"' | '\\'' | '\\\\' ) | '\\\\' 'x' ( HexDigit )+ | OctalEscape )
            int alt38 = 3;
            int LA38_0 = input.LA(1);

            if ((LA38_0 == '\\')) {
                switch (input.LA(2)) {
                    case '\"':
                    case '\'':
                    case '\\':
                    case 'b':
                    case 'f':
                    case 'n':
                    case 'r':
                    case 't':
                    case 'v': {
                        alt38 = 1;
                    }
                    break;
                    case 'x': {
                        alt38 = 2;
                    }
                    break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7': {
                        alt38 = 3;
                    }
                    break;
                    default:
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 38, 1, input);

                        throw nvae;

                }

            } else {
                if (backtracking > 0) {
                    failed = true;
                    return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:607:6: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'v' | '\\\"' | '\\'' | '\\\\' )
                {
                    match('\\');
                    if (failed) {
                        return;
                    }

                    if (input.LA(1) == '\"' || input.LA(1) == '\'' || input.LA(1) == '\\' || input.LA(1) == 'b' || input.LA(1) == 'f' || input.LA(1) == 'n' || input.LA(1) == 'r' || input.LA(1) == 't' || input.LA(1) == 'v') {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:608:5: '\\\\' 'x' ( HexDigit )+
                {
                    match('\\');
                    if (failed) {
                        return;
                    }

                    match('x');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:608:14: ( HexDigit )+
                    int cnt37 = 0;
                    loop37:
                    do {
                        int alt37 = 2;
                        int LA37_0 = input.LA(1);

                        if (((LA37_0 >= '0' && LA37_0 <= '9') || (LA37_0 >= 'A' && LA37_0 <= 'F') || (LA37_0 >= 'a' && LA37_0 <= 'f'))) {
                            alt37 = 1;
                        }


                        switch (alt37) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                            {
                                if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F') || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                                    input.consume();
                                    failed = false;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    MismatchedSetException mse = new MismatchedSetException(null, input);
                                    recover(mse);
                                    throw mse;
                                }


                            }
                            break;

                            default:
                                if (cnt37 >= 1) {
                                    break loop37;
                                }
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                EarlyExitException eee =
                                        new EarlyExitException(37, input);
                                throw eee;
                        }
                        cnt37++;
                    } while (true);


                }
                break;
                case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:609:7: OctalEscape
                {
                    mOctalEscape();
                    if (failed) {
                        return;
                    }


                }
                break;

            }

        } finally {
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:613:3: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt39 = 3;
            int LA39_0 = input.LA(1);

            if ((LA39_0 == '\\')) {
                int LA39_1 = input.LA(2);

                if (((LA39_1 >= '0' && LA39_1 <= '3'))) {
                    int LA39_2 = input.LA(3);

                    if (((LA39_2 >= '0' && LA39_2 <= '7'))) {
                        int LA39_4 = input.LA(4);

                        if (((LA39_4 >= '0' && LA39_4 <= '7'))) {
                            alt39 = 1;
                        } else {
                            alt39 = 2;
                        }
                    } else {
                        alt39 = 3;
                    }
                } else if (((LA39_1 >= '4' && LA39_1 <= '7'))) {
                    int LA39_3 = input.LA(3);

                    if (((LA39_3 >= '0' && LA39_3 <= '7'))) {
                        alt39 = 2;
                    } else {
                        alt39 = 3;
                    }
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 39, 1, input);

                    throw nvae;

                }
            } else {
                if (backtracking > 0) {
                    failed = true;
                    return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 39, 0, input);

                throw nvae;

            }
            switch (alt39) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:613:5: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                {
                    match('\\');
                    if (failed) {
                        return;
                    }

                    if ((input.LA(1) >= '0' && input.LA(1) <= '3')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                    if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                    if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:614:5: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                {
                    match('\\');
                    if (failed) {
                        return;
                    }

                    if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                    if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                }
                break;
                case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:615:5: '\\\\' ( '0' .. '7' )
                {
                    match('\\');
                    if (failed) {
                        return;
                    }

                    if ((input.LA(1) >= '0' && input.LA(1) <= '7')) {
                        input.consume();
                        failed = false;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        MismatchedSetException mse = new MismatchedSetException(null, input);
                        recover(mse);
                        throw mse;
                    }


                }
                break;

            }

        } finally {
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:619:3: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:619:5: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
                match('\\');
                if (failed) {
                    return;
                }

                match('u');
                if (failed) {
                    return;
                }

                mHexDigit();
                if (failed) {
                    return;
                }


                mHexDigit();
                if (failed) {
                    return;
                }


                mHexDigit();
                if (failed) {
                    return;
                }


                mHexDigit();
                if (failed) {
                    return;
                }


            }


        } finally {
        }
    }
    // $ANTLR end "UnicodeEscape"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:623:3: ( '/*' ( options {greedy=false; } : . )* '*/' | '/' '\\\\' '\\n' '*' ( options {greedy=false; } : . )* '*/' )
            int alt42 = 2;
            int LA42_0 = input.LA(1);

            if ((LA42_0 == '/')) {
                int LA42_1 = input.LA(2);

                if ((LA42_1 == '*')) {
                    alt42 = 1;
                } else if ((LA42_1 == '\\')) {
                    alt42 = 2;
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 42, 1, input);

                    throw nvae;

                }
            } else {
                if (backtracking > 0) {
                    failed = true;
                    return;
                }
                NoViableAltException nvae =
                        new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:623:5: '/*' ( options {greedy=false; } : . )* '*/'
                {
                    match("/*");
                    if (failed) {
                        return;
                    }



                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:623:11: ( options {greedy=false; } : . )*
                    loop40:
                    do {
                        int alt40 = 2;
                        int LA40_0 = input.LA(1);

                        if ((LA40_0 == '*')) {
                            int LA40_1 = input.LA(2);

                            if ((LA40_1 == '/')) {
                                alt40 = 2;
                            } else if (((LA40_1 >= '\u0000' && LA40_1 <= '.') || (LA40_1 >= '0' && LA40_1 <= '\uFFFF'))) {
                                alt40 = 1;
                            }


                        } else if (((LA40_0 >= '\u0000' && LA40_0 <= ')') || (LA40_0 >= '+' && LA40_0 <= '\uFFFF'))) {
                            alt40 = 1;
                        }


                        switch (alt40) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:623:37: .
                            {
                                matchAny();
                                if (failed) {
                                    return;
                                }

                            }
                            break;

                            default:
                                break loop40;
                        }
                    } while (true);


                    match("*/");
                    if (failed) {
                        return;
                    }



                    if (backtracking == 0) {
                        _channel = 99;
                    }

                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:624:5: '/' '\\\\' '\\n' '*' ( options {greedy=false; } : . )* '*/'
                {
                    match('/');
                    if (failed) {
                        return;
                    }

                    match('\\');
                    if (failed) {
                        return;
                    }

                    match('\n');
                    if (failed) {
                        return;
                    }

                    match('*');
                    if (failed) {
                        return;
                    }

                    // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:624:24: ( options {greedy=false; } : . )*
                    loop41:
                    do {
                        int alt41 = 2;
                        int LA41_0 = input.LA(1);

                        if ((LA41_0 == '*')) {
                            int LA41_1 = input.LA(2);

                            if ((LA41_1 == '/')) {
                                alt41 = 2;
                            } else if (((LA41_1 >= '\u0000' && LA41_1 <= '.') || (LA41_1 >= '0' && LA41_1 <= '\uFFFF'))) {
                                alt41 = 1;
                            }


                        } else if (((LA41_0 >= '\u0000' && LA41_0 <= ')') || (LA41_0 >= '+' && LA41_0 <= '\uFFFF'))) {
                            alt41 = 1;
                        }


                        switch (alt41) {
                            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:624:50: .
                            {
                                matchAny();
                                if (failed) {
                                    return;
                                }

                            }
                            break;

                            default:
                                break loop41;
                        }
                    } while (true);


                    match("*/");
                    if (failed) {
                        return;
                    }



                    if (backtracking == 0) {
                        _channel = 99;
                    }

                }
                break;

            }
            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:628:3: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:628:5: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
                match("//");
                if (failed) {
                    return;
                }



                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:628:10: (~ ( '\\n' | '\\r' ) )*
                loop43:
                do {
                    int alt43 = 2;
                    int LA43_0 = input.LA(1);

                    if (((LA43_0 >= '\u0000' && LA43_0 <= '\t') || (LA43_0 >= '\u000B' && LA43_0 <= '\f') || (LA43_0 >= '\u000E' && LA43_0 <= '\uFFFF'))) {
                        alt43 = 1;
                    }


                    switch (alt43) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\f') || (input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF')) {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop43;
                    }
                } while (true);


                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:628:24: ( '\\r' )?
                int alt44 = 2;
                int LA44_0 = input.LA(1);

                if ((LA44_0 == '\r')) {
                    alt44 = 1;
                }
                switch (alt44) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:628:24: '\\r'
                    {
                        match('\r');
                        if (failed) {
                            return;
                        }

                    }
                    break;

                }


                match('\n');
                if (failed) {
                    return;
                }

                if (backtracking == 0) {
                    if (!inDirective) {
                        try {
                            if (input.LT(1) != '#' && input.LT(1) != -1) {
                                _type = TEXT_END;
                            } else {
                                _type = End;
                            }
                        } catch (Exception e) {
                            _type = End;
                        }
                    } else {
                        _type = End;
                    }

                    inDirective = false;
                    inDefineMode = false;
                    discardSpace = true;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:658:5: ( ( ' ' | '\\r' | '\\t' | '\\f' )+ )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:659:3: ( ' ' | '\\r' | '\\t' | '\\f' )+
            {
                if (backtracking == 0) {
                    if (this.getLine() != 1) {
                        if (input.LA(-1) == '\n') {
                            ltoken = End;
                        } else {
                            ltoken = input.LA(-1);
                        }
                    } else {
                        try {
                            if (input.LA(-1) == '\n') {
                                ltoken = End;
                            } else {
                                ltoken = input.LA(-1);
                            }
                        } catch (Exception e) {
                            ltoken = End;
                        }
                    }

                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:683:3: ( ' ' | '\\r' | '\\t' | '\\f' )+
                int cnt45 = 0;
                loop45:
                do {
                    int alt45 = 2;
                    int LA45_0 = input.LA(1);

                    if ((LA45_0 == '\t' || (LA45_0 >= '\f' && LA45_0 <= '\r') || LA45_0 == ' ')) {
                        alt45 = 1;
                    }


                    switch (alt45) {
                        case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:
                        {
                            if (input.LA(1) == '\t' || (input.LA(1) >= '\f' && input.LA(1) <= '\r') || input.LA(1) == ' ') {
                                input.consume();
                                failed = false;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt45 >= 1) {
                                break loop45;
                            }
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(45, input);
                            throw eee;
                    }
                    cnt45++;
                } while (true);


                if (backtracking == 0) {
                    if (inDirective == true) {
                        if (discardSpace == true) {
                            _channel = 99;
                        } else {
                            _type = WS;
                        }
                    } else {
                        if (!inDefineMode) {
                            try {
                                if (input.LA(1) != EOF && input.LA(1) == '#' && ltoken == End) {
                                    _type = End;
                                }
                            } catch (Exception e) {
                                _channel = 99;
                            }
                        }
                    }
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "End"
    public final void mEnd() throws RecognitionException {
        try {
            int _type = End;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:720:6: ( ( WS )? '\\n' )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:720:8: ( WS )? '\\n'
            {
                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:720:8: ( WS )?
                int alt46 = 2;
                int LA46_0 = input.LA(1);

                if ((LA46_0 == '\t' || (LA46_0 >= '\f' && LA46_0 <= '\r') || LA46_0 == ' ')) {
                    alt46 = 1;
                }
                switch (alt46) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:720:8: WS
                    {
                        mWS();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


                match('\n');
                if (failed) {
                    return;
                }

                if (backtracking == 0) {
                    if (!inDirective) {
                        if (inDefineMode) {
                            _type = End;
                        } else {
                            try {
                                if (input.LT(1) != '#' && input.LT(1) != -1) {
                                    _type = TEXT_END;
                                }
                            } catch (Exception e) {
                                _type = End;
                            }
                        }
                    } else {
                        _type = End;
                    }
                    ltoken = End;
                    inDirective = false;
                    inDefineMode = false;
                    discardSpace = true;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "End"

    // $ANTLR start "ESCAPED_NEWLINE"
    public final void mESCAPED_NEWLINE() throws RecognitionException {
        try {
            int _type = ESCAPED_NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:750:3: ( ( '\\\\\\n' | '\\\\\\r\\n' ) )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:750:5: ( '\\\\\\n' | '\\\\\\r\\n' )
            {
                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:750:5: ( '\\\\\\n' | '\\\\\\r\\n' )
                int alt47 = 2;
                int LA47_0 = input.LA(1);

                if ((LA47_0 == '\\')) {
                    int LA47_1 = input.LA(2);

                    if ((LA47_1 == '\n')) {
                        alt47 = 1;
                    } else if ((LA47_1 == '\r')) {
                        alt47 = 2;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 47, 1, input);

                        throw nvae;

                    }
                } else {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 47, 0, input);

                    throw nvae;

                }
                switch (alt47) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:750:6: '\\\\\\n'
                    {
                        match("\\\n");
                        if (failed) {
                            return;
                        }



                    }
                    break;
                    case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:750:15: '\\\\\\r\\n'
                    {
                        match("\\\r\n");
                        if (failed) {
                            return;
                        }



                    }
                    break;

                }


                if (backtracking == 0) {
                    _channel = 99;
                }

            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "ESCAPED_NEWLINE"

    // $ANTLR start "COPERATOR"
    public final void mCOPERATOR() throws RecognitionException {
        try {
            int _type = COPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:757:3: ({...}? ( COLON | QUESTIONMARK | POINTERTO | LCURLY | RCURLY | LSQUARE | RSQUARE | STAR | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUSPLUS | MINUSMINUS | MOD | SHIFTRIGHT | SHIFTLEFT | AND | OR | BITWISEOR | BITWISEXOR | DOT | POINTERTOMBR | DOTMBR | SCOPE | AMPERSAND | PLUS | MINUS | TILDE | ASSIGNEQUAL | TIMESEQUAL | DIVIDEEQUAL | MODEQUAL | PLUSEQUAL | MINUSEQUAL | SHIFTLEFTEQUAL | SHIFTRIGHTEQUAL | BITWISEANDEQUAL | BITWISEXOREQUAL | BITWISEOREQUAL | NOT | ELLIPSIS ) )
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:757:7: {...}? ( COLON | QUESTIONMARK | POINTERTO | LCURLY | RCURLY | LSQUARE | RSQUARE | STAR | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUSPLUS | MINUSMINUS | MOD | SHIFTRIGHT | SHIFTLEFT | AND | OR | BITWISEOR | BITWISEXOR | DOT | POINTERTOMBR | DOTMBR | SCOPE | AMPERSAND | PLUS | MINUS | TILDE | ASSIGNEQUAL | TIMESEQUAL | DIVIDEEQUAL | MODEQUAL | PLUSEQUAL | MINUSEQUAL | SHIFTLEFTEQUAL | SHIFTRIGHTEQUAL | BITWISEANDEQUAL | BITWISEXOREQUAL | BITWISEOREQUAL | NOT | ELLIPSIS )
            {
                if (!((!inDirective))) {
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    throw new FailedPredicateException(input, "COPERATOR", "!inDirective");
                }

                // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:758:9: ( COLON | QUESTIONMARK | POINTERTO | LCURLY | RCURLY | LSQUARE | RSQUARE | STAR | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUSPLUS | MINUSMINUS | MOD | SHIFTRIGHT | SHIFTLEFT | AND | OR | BITWISEOR | BITWISEXOR | DOT | POINTERTOMBR | DOTMBR | SCOPE | AMPERSAND | PLUS | MINUS | TILDE | ASSIGNEQUAL | TIMESEQUAL | DIVIDEEQUAL | MODEQUAL | PLUSEQUAL | MINUSEQUAL | SHIFTLEFTEQUAL | SHIFTRIGHTEQUAL | BITWISEANDEQUAL | BITWISEXOREQUAL | BITWISEOREQUAL | NOT | ELLIPSIS )
                int alt48 = 45;
                alt48 = dfa48.predict(input);
                switch (alt48) {
                    case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:758:13: COLON
                    {
                        mCOLON();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:758:37: QUESTIONMARK
                    {
                        mQUESTIONMARK();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:759:13: POINTERTO
                    {
                        mPOINTERTO();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:759:27: LCURLY
                    {
                        mLCURLY();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 5: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:759:39: RCURLY
                    {
                        mRCURLY();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 6: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:760:5: LSQUARE
                    {
                        mLSQUARE();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 7: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:760:18: RSQUARE
                    {
                        mRSQUARE();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 8: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:760:39: STAR
                    {
                        mSTAR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 9: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:761:13: EQUAL
                    {
                        mEQUALITY_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 10: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:761:37: NOTEQUAL
                    {
                        mINEQUALITY_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 11: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:761:61: LESSTHANOREQUALTO
                    {
                        mLESS_OR_EQUAL_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 12: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:761:85: LESSTHAN
                    {
                        mLOWER_THAN_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 13: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:762:13: GREATERTHANOREQUALTO
                    {
                        mGREATER_OR_EQUAL_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 14: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:762:37: GREATERTHAN
                    {
                        mGREATER_THAN_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 15: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:762:61: DIVIDE
                    {
                        mDIVIDE();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 16: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:762:85: PLUSPLUS
                    {
                        mPLUSPLUS();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 17: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:763:13: MINUSMINUS
                    {
                        mMINUSMINUS();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 18: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:763:37: MOD
                    {
                        mMOD();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 19: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:763:61: SHIFTRIGHT
                    {
                        mSHIFTRIGHT();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 20: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:763:85: SHIFTLEFT
                    {
                        mSHIFTLEFT();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 21: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:764:13: AND
                    {
                        mAND();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 22: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:764:37: OR
                    {
                        mOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 23: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:764:61: BITWISEOR
                    {
                        mBITWISEOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 24: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:764:85: BITWISEXOR
                    {
                        mBITWISEXOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 25: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:765:13: DOT
                    {
                        mDOT();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 26: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:765:37: POINTERTOMBR
                    {
                        mPOINTERTOMBR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 27: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:765:61: DOTMBR
                    {
                        mDOTMBR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 28: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:765:85: SCOPE
                    {
                        mSCOPE();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 29: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:766:13: AMPERSAND
                    {
                        mAMPERSAND();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 30: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:766:37: PLUS
                    {
                        mPLUS();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 31: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:766:61: MINUS
                    {
                        mMINUS();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 32: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:767:13: TILDE
                    {
                        mTILDE();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 33: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:767:37: ASSIGNEQUAL
                    {
                        mASSIGNEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 34: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:767:61: TIMESEQUAL
                    {
                        mTIMESEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 35: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:767:85: DIVIDEEQUAL
                    {
                        mDIVIDEEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 36: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:768:13: MODEQUAL
                    {
                        mMODEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 37: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:768:37: PLUSEQUAL
                    {
                        mPLUSEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 38: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:768:61: MINUSEQUAL
                    {
                        mMINUSEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 39: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:768:85: SHIFTLEFTEQUAL
                    {
                        mSHIFTLEFTEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 40: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:769:13: SHIFTRIGHTEQUAL
                    {
                        mSHIFTRIGHTEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 41: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:769:37: BITWISEANDEQUAL
                    {
                        mBITWISEANDEQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 42: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:769:61: BITWISEXOREQUAL
                    {
                        mBITWISEXOREQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 43: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:769:85: BITWISEOREQUAL
                    {
                        mBITWISEOREQUAL();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 44: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:770:5: NOT
                    {
                        mNEGATION_OPERATOR();
                        if (failed) {
                            return;
                        }


                    }
                    break;
                    case 45: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:770:15: ELLIPSIS
                    {
                        mELLIPSIS();
                        if (failed) {
                            return;
                        }


                    }
                    break;

                }


            }

            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "COPERATOR"

    // $ANTLR start "CKEYWORD"
    public final void mCKEYWORD() throws RecognitionException {
        try {
            int _type = CKEYWORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:774:10: ({...}? 'typedef' | '__va_list__' | 'extern' | 'static' | 'auto' | 'register' | 'void' | 'char' | 'short' | 'int' | 'long' | 'float' | 'double' | 'signed' | 'unsigned' | '__fpreg' | '__float80' | 'struct' | 'union' | 'enum' | 'const' | 'volatile' | 'case' | 'default' | 'switch' | 'while' | 'do' | 'for' | 'goto' | 'continue' | 'break' | 'return' | 'if' | 'else' )
            int alt49 = 34;
            switch (input.LA(1)) {
                case 't': {
                    alt49 = 1;
                }
                break;
                case '_': {
                    int LA49_2 = input.LA(2);

                    if ((LA49_2 == '_')) {
                        int LA49_17 = input.LA(3);

                        if ((LA49_17 == 'v')) {
                            alt49 = 2;
                        } else if ((LA49_17 == 'f')) {
                            int LA49_38 = input.LA(4);

                            if ((LA49_38 == 'p')) {
                                alt49 = 16;
                            } else if ((LA49_38 == 'l')) {
                                alt49 = 17;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                NoViableAltException nvae =
                                        new NoViableAltException("", 49, 38, input);

                                throw nvae;

                            }
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 17, input);

                            throw nvae;

                        }
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 2, input);

                        throw nvae;

                    }
                }
                break;
                case 'e': {
                    switch (input.LA(2)) {
                        case 'x': {
                            alt49 = 3;
                        }
                        break;
                        case 'n': {
                            alt49 = 20;
                        }
                        break;
                        case 'l': {
                            alt49 = 34;
                        }
                        break;
                        default:
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 3, input);

                            throw nvae;

                    }

                }
                break;
                case 's': {
                    switch (input.LA(2)) {
                        case 't': {
                            int LA49_21 = input.LA(3);

                            if ((LA49_21 == 'a')) {
                                alt49 = 4;
                            } else if ((LA49_21 == 'r')) {
                                alt49 = 18;
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                NoViableAltException nvae =
                                        new NoViableAltException("", 49, 21, input);

                                throw nvae;

                            }
                        }
                        break;
                        case 'h': {
                            alt49 = 9;
                        }
                        break;
                        case 'i': {
                            alt49 = 14;
                        }
                        break;
                        case 'w': {
                            alt49 = 25;
                        }
                        break;
                        default:
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 4, input);

                            throw nvae;

                    }

                }
                break;
                case 'a': {
                    alt49 = 5;
                }
                break;
                case 'r': {
                    int LA49_6 = input.LA(2);

                    if ((LA49_6 == 'e')) {
                        int LA49_25 = input.LA(3);

                        if ((LA49_25 == 'g')) {
                            alt49 = 6;
                        } else if ((LA49_25 == 't')) {
                            alt49 = 32;
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 25, input);

                            throw nvae;

                        }
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 6, input);

                        throw nvae;

                    }
                }
                break;
                case 'v': {
                    int LA49_7 = input.LA(2);

                    if ((LA49_7 == 'o')) {
                        int LA49_26 = input.LA(3);

                        if ((LA49_26 == 'i')) {
                            alt49 = 7;
                        } else if ((LA49_26 == 'l')) {
                            alt49 = 22;
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 26, input);

                            throw nvae;

                        }
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 7, input);

                        throw nvae;

                    }
                }
                break;
                case 'c': {
                    switch (input.LA(2)) {
                        case 'h': {
                            alt49 = 8;
                        }
                        break;
                        case 'o': {
                            int LA49_28 = input.LA(3);

                            if ((LA49_28 == 'n')) {
                                int LA49_45 = input.LA(4);

                                if ((LA49_45 == 's')) {
                                    alt49 = 21;
                                } else if ((LA49_45 == 't')) {
                                    alt49 = 30;
                                } else {
                                    if (backtracking > 0) {
                                        failed = true;
                                        return;
                                    }
                                    NoViableAltException nvae =
                                            new NoViableAltException("", 49, 45, input);

                                    throw nvae;

                                }
                            } else {
                                if (backtracking > 0) {
                                    failed = true;
                                    return;
                                }
                                NoViableAltException nvae =
                                        new NoViableAltException("", 49, 28, input);

                                throw nvae;

                            }
                        }
                        break;
                        case 'a': {
                            alt49 = 23;
                        }
                        break;
                        default:
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 8, input);

                            throw nvae;

                    }

                }
                break;
                case 'i': {
                    int LA49_9 = input.LA(2);

                    if ((LA49_9 == 'n')) {
                        alt49 = 10;
                    } else if ((LA49_9 == 'f')) {
                        alt49 = 33;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 9, input);

                        throw nvae;

                    }
                }
                break;
                case 'l': {
                    alt49 = 11;
                }
                break;
                case 'f': {
                    int LA49_11 = input.LA(2);

                    if ((LA49_11 == 'l')) {
                        alt49 = 12;
                    } else if ((LA49_11 == 'o')) {
                        alt49 = 28;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 11, input);

                        throw nvae;

                    }
                }
                break;
                case 'd': {
                    int LA49_12 = input.LA(2);

                    if ((LA49_12 == 'o')) {
                        int LA49_34 = input.LA(3);

                        if ((LA49_34 == 'u')) {
                            alt49 = 13;
                        } else {
                            alt49 = 27;
                        }
                    } else if ((LA49_12 == 'e')) {
                        alt49 = 24;
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 12, input);

                        throw nvae;

                    }
                }
                break;
                case 'u': {
                    int LA49_13 = input.LA(2);

                    if ((LA49_13 == 'n')) {
                        int LA49_36 = input.LA(3);

                        if ((LA49_36 == 's')) {
                            alt49 = 15;
                        } else if ((LA49_36 == 'i')) {
                            alt49 = 19;
                        } else {
                            if (backtracking > 0) {
                                failed = true;
                                return;
                            }
                            NoViableAltException nvae =
                                    new NoViableAltException("", 49, 36, input);

                            throw nvae;

                        }
                    } else {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        NoViableAltException nvae =
                                new NoViableAltException("", 49, 13, input);

                        throw nvae;

                    }
                }
                break;
                case 'w': {
                    alt49 = 26;
                }
                break;
                case 'g': {
                    alt49 = 29;
                }
                break;
                case 'b': {
                    alt49 = 31;
                }
                break;
                default:
                    if (backtracking > 0) {
                        failed = true;
                        return;
                    }
                    NoViableAltException nvae =
                            new NoViableAltException("", 49, 0, input);

                    throw nvae;

            }

            switch (alt49) {
                case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:774:12: {...}? 'typedef'
                {
                    if (!((!inDirective))) {
                        if (backtracking > 0) {
                            failed = true;
                            return;
                        }
                        throw new FailedPredicateException(input, "CKEYWORD", "!inDirective");
                    }

                    match("typedef");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:775:18: '__va_list__'
                {
                    match("__va_list__");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:775:34: 'extern'
                {
                    match("extern");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:775:45: 'static'
                {
                    match("static");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 5: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:775:56: 'auto'
                {
                    match("auto");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 6: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:775:66: 'register'
                {
                    match("register");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 7: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:7: 'void'
                {
                    match("void");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 8: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:18: 'char'
                {
                    match("char");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 9: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:28: 'short'
                {
                    match("short");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 10: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:38: 'int'
                {
                    match("int");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 11: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:47: 'long'
                {
                    match("long");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 12: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:776:57: 'float'
                {
                    match("float");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 13: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:7: 'double'
                {
                    match("double");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 14: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:18: 'signed'
                {
                    match("signed");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 15: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:30: 'unsigned'
                {
                    match("unsigned");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 16: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:42: '__fpreg'
                {
                    match("__fpreg");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 17: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:54: '__float80'
                {
                    match("__float80");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 18: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:777:69: 'struct'
                {
                    match("struct");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 19: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:7: 'union'
                {
                    match("union");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 20: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:18: 'enum'
                {
                    match("enum");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 21: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:28: 'const'
                {
                    match("const");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 22: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:38: 'volatile'
                {
                    match("volatile");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 23: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:51: 'case'
                {
                    match("case");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 24: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:778:62: 'default'
                {
                    match("default");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 25: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:7: 'switch'
                {
                    mSWITCH();
                    if (failed) {
                        return;
                    }



                }
                break;
                case 26: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:18: 'while'
                {
                    mWHILE();
                    if (failed) {
                        return;
                    }



                }
                break;
                case 27: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:29: 'do'
                {
                    match("do");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 28: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:38: 'for'
                {
                   mFOR();
                    if (failed) {
                        return;
                    }
                }
                break;
                case 29: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:47: 'goto'
                {
                    match("goto");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 30: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:779:57: 'continue'
                {
                    match("continue");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 31: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:780:7: 'break'
                {
                    match("break");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 32: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:780:18: 'return'
                {
                    match("return");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 33: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:780:31: 'if'
                {
                    match("if");
                    if (failed) {
                        return;
                    }



                }
                break;
                case 34: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:780:40: 'else'
                {
                    match("else");
                    if (failed) {
                        return;
                    }



                }
                break;

            }
            type = _type;
            channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "CKEYWORD"

    public void mTokens() throws RecognitionException {
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:8: ( STRING_OP | DIRECTIVE | SIZEOF | DEFINED | IDENTIFIER | STRINGIFICATION | SHARPSHARP | ASSIGNEQUAL | COLON | COMMA | QUESTIONMARK | SEMICOLON | POINTERTO | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | DIVIDEEQUAL | PLUS | PLUSEQUAL | PLUSPLUS | MINUS | MINUSEQUAL | MINUSMINUS | STAR | TIMESEQUAL | MOD | MODEQUAL | SHIFTRIGHT | SHIFTRIGHTEQUAL | SHIFTLEFT | SHIFTLEFTEQUAL | AND | NOT | OR | AMPERSAND | BITWISEANDEQUAL | TILDE | BITWISEOR | BITWISEOREQUAL | BITWISEXOR | BITWISEXOREQUAL | DOT | POINTERTOMBR | DOTMBR | SCOPE | ELLIPSIS | CHARACTER_LITERAL | STRING_LITERAL | HEX_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL | FLOATING_POINT_LITERAL | COMMENT | LINE_COMMENT | WS | End | ESCAPED_NEWLINE | COPERATOR | CKEYWORD )
        int alt50 = 69;
        alt50 = dfa50.predict(input);
        switch (alt50) {
            case 1: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:10: STRING_OP
            {
                mSTRING_OP();
                if (failed) {
                    return;
                }


            }
            break;
            case 2: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:20: DIRECTIVE
            {
                mDIRECTIVE();
                if (failed) {
                    return;
                }


            }
            break;
            case 3: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:30: SIZEOF
            {
                mSIZEOF();
                if (failed) {
                    return;
                }


            }
            break;
            case 4: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:37: DEFINED
            {
                mDEFINED();
                if (failed) {
                    return;
                }


            }
            break;
            case 5: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:45: IDENTIFIER
            {
                mIDENTIFIER();
                if (failed) {
                    return;
                }


            }
            break;
            case 6: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:56: STRINGIFICATION
            {
                mSTRINGIFICATION();
                if (failed) {
                    return;
                }


            }
            break;
            case 7: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:72: SHARPSHARP
            {
                mSHARPSHARP();
                if (failed) {
                    return;
                }


            }
            break;
            case 8: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:83: ASSIGNEQUAL
            {
                mASSIGNEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 9: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:95: COLON
            {
                mCOLON();
                if (failed) {
                    return;
                }


            }
            break;
            case 10: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:101: COMMA
            {
                mCOMMA();
                if (failed) {
                    return;
                }


            }
            break;
            case 11: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:107: QUESTIONMARK
            {
                mQUESTIONMARK();
                if (failed) {
                    return;
                }


            }
            break;
            case 12: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:120: SEMICOLON
            {
                mSEMICOLON();
                if (failed) {
                    return;
                }


            }
            break;
            case 13: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:130: POINTERTO
            {
                mPOINTERTO();
                if (failed) {
                    return;
                }


            }
            break;
            case 14: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:140: LPAREN
            {
                mLPAREN();
                if (failed) {
                    return;
                }


            }
            break;
            case 15: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:147: RPAREN
            {
                mRPAREN();
                if (failed) {
                    return;
                }


            }
            break;
            case 16: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:154: LSQUARE
            {
                mLSQUARE();
                if (failed) {
                    return;
                }


            }
            break;
            case 17: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:162: RSQUARE
            {
                mRSQUARE();
                if (failed) {
                    return;
                }


            }
            break;
            case 18: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:170: LCURLY
            {
                mLCURLY();
                if (failed) {
                    return;
                }


            }
            break;
            case 19: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:177: RCURLY
            {
                mRCURLY();
                if (failed) {
                    return;
                }


            }
            break;
            case 20: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:184: EQUAL
            {
                mEQUALITY_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 21: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:190: NOTEQUAL
            {
                mINEQUALITY_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 22: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:199: LESSTHANOREQUALTO
            {
                mLESS_OR_EQUAL_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 23: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:217: LESSTHAN
            {
                mLOWER_THAN_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 24: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:226: GREATERTHANOREQUALTO
            {
                mGREATER_OR_EQUAL_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 25: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:247: GREATERTHAN
            {
                mGREATER_THAN_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 26: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:259: DIVIDE
            {
                mDIVIDE();
                if (failed) {
                    return;
                }


            }
            break;
            case 27: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:266: DIVIDEEQUAL
            {
                mDIVIDEEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 28: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:278: PLUS
            {
                mPLUS();
                if (failed) {
                    return;
                }


            }
            break;
            case 29: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:283: PLUSEQUAL
            {
                mPLUSEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 30: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:293: PLUSPLUS
            {
                mPLUSPLUS();
                if (failed) {
                    return;
                }


            }
            break;
            case 31: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:302: MINUS
            {
                mMINUS();
                if (failed) {
                    return;
                }


            }
            break;
            case 32: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:308: MINUSEQUAL
            {
                mMINUSEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 33: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:319: MINUSMINUS
            {
                mMINUSMINUS();
                if (failed) {
                    return;
                }


            }
            break;
            case 34: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:330: STAR
            {
                mSTAR();
                if (failed) {
                    return;
                }


            }
            break;
            case 35: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:335: TIMESEQUAL
            {
                mTIMESEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 36: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:346: MOD
            {
                mMOD();
                if (failed) {
                    return;
                }


            }
            break;
            case 37: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:350: MODEQUAL
            {
                mMODEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 38: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:359: SHIFTRIGHT
            {
                mSHIFTRIGHT();
                if (failed) {
                    return;
                }


            }
            break;
            case 39: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:370: SHIFTRIGHTEQUAL
            {
                mSHIFTRIGHTEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 40: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:386: SHIFTLEFT
            {
                mSHIFTLEFT();
                if (failed) {
                    return;
                }


            }
            break;
            case 41: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:396: SHIFTLEFTEQUAL
            {
                mSHIFTLEFTEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 42: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:411: AND
            {
                mAND();
                if (failed) {
                    return;
                }


            }
            break;
            case 43: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:415: NOT
            {
                mNEGATION_OPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 44: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:419: OR
            {
                mOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 45: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:422: AMPERSAND
            {
                mAMPERSAND();
                if (failed) {
                    return;
                }


            }
            break;
            case 46: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:432: BITWISEANDEQUAL
            {
                mBITWISEANDEQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 47: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:448: TILDE
            {
                mTILDE();
                if (failed) {
                    return;
                }


            }
            break;
            case 48: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:454: BITWISEOR
            {
                mBITWISEOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 49: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:464: BITWISEOREQUAL
            {
                mBITWISEOREQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 50: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:479: BITWISEXOR
            {
                mBITWISEXOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 51: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:490: BITWISEXOREQUAL
            {
                mBITWISEXOREQUAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 52: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:506: DOT
            {
                mDOT();
                if (failed) {
                    return;
                }


            }
            break;
            case 53: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:510: POINTERTOMBR
            {
                mPOINTERTOMBR();
                if (failed) {
                    return;
                }


            }
            break;
            case 54: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:523: DOTMBR
            {
                mDOTMBR();
                if (failed) {
                    return;
                }


            }
            break;
            case 55: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:530: SCOPE
            {
                mSCOPE();
                if (failed) {
                    return;
                }


            }
            break;
            case 56: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:536: ELLIPSIS
            {
                mELLIPSIS();
                if (failed) {
                    return;
                }


            }
            break;
            case 57: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:545: CHARACTER_LITERAL
            {
                mCHARACTER_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 58: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:563: STRING_LITERAL
            {
                mSTRING_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 59: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:578: HEX_LITERAL
            {
                mHEX_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 60: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:590: DECIMAL_LITERAL
            {
                mDECIMAL_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 61: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:606: OCTAL_LITERAL
            {
                mOCTAL_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 62: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:620: FLOATING_POINT_LITERAL
            {
                mFLOATING_POINT_LITERAL();
                if (failed) {
                    return;
                }


            }
            break;
            case 63: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:643: COMMENT
            {
                mCOMMENT();
                if (failed) {
                    return;
                }


            }
            break;
            case 64: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:651: LINE_COMMENT
            {
                mLINE_COMMENT();
                if (failed) {
                    return;
                }


            }
            break;
            case 65: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:664: WS
            {
                mWS();
                if (failed) {
                    return;
                }


            }
            break;
            case 66: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:667: End
            {
                mEnd();
                if (failed) {
                    return;
                }


            }
            break;
            case 67: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:671: ESCAPED_NEWLINE
            {
                mESCAPED_NEWLINE();
                if (failed) {
                    return;
                }


            }
            break;
            case 68: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:687: COPERATOR
            {
                mCOPERATOR();
                if (failed) {
                    return;
                }


            }
            break;
            case 69: // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:1:697: CKEYWORD
            {
                mCKEYWORD();
                if (failed) {
                    return;
                }


            }
            break;

        }

    }

    // $ANTLR start synpred1_Cpp
    public final void synpred1_Cpp_fragment() throws RecognitionException {
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:21: ( '/*' )
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:391:22: '/*'
        {
            match("/*");
            if (failed) {
                return;
            }



        }

    }
    // $ANTLR end synpred1_Cpp

    // $ANTLR start synpred2_Cpp
    public final void synpred2_Cpp_fragment() throws RecognitionException {
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:392:17: ( '\\\\\\n' )
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:392:18: '\\\\\\n'
        {
            match("\\\n");
            if (failed) {
                return;
            }



        }

    }
    // $ANTLR end synpred2_Cpp

    // $ANTLR start synpred3_Cpp
    public final void synpred3_Cpp_fragment() throws RecognitionException {
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:393:17: ( '\\\\\\r\\n' )
        // D:\\TESIS\\ANTLR\\Gramáticas\\Cpp.g:393:18: '\\\\\\r\\n'
        {
            match("\\\r\n");
            if (failed) {
                return;
            }



        }

    }
    // $ANTLR end synpred3_Cpp

    public final boolean synpred2_Cpp() {
        backtracking++;
        int start = input.mark();
        try {
            synpred2_Cpp_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed = false;
        return success;
    }

    public final boolean synpred3_Cpp() {
        backtracking++;
        int start = input.mark();
        try {
            synpred3_Cpp_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed = false;
        return success;
    }

    public final boolean synpred1_Cpp() {
        backtracking++;
        int start = input.mark();
        try {
            synpred1_Cpp_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed = false;
        return success;
    }
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA34 dfa34 = new DFA34(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA50 dfa50 = new DFA50(this);
    static final String DFA6_eotS =
            "\1\10\10\uffff\1\21\27\uffff";
    static final String DFA6_eofS =
            "\41\uffff";
    static final String DFA6_minS =
            "\1\144\1\146\1\154\6\uffff\1\144\1\143\1\151\6\uffff\1\154\2\uffff"
            + "\1\165\1\144\1\145\1\11\1\156\1\11\1\145\2\uffff\1\170\1\164\1\11";
    static final String DFA6_maxS =
            "\1\167\1\156\1\170\6\uffff\1\156\1\143\1\163\6\uffff\1\154\2\uffff"
            + "\1\165\1\144\1\145\1\137\1\156\1\172\1\145\2\uffff\1\170\1\164\1"
            + "\40";
    static final String DFA6_acceptS =
            "\3\uffff\1\7\1\10\1\11\1\15\1\17\1\20\3\uffff\1\6\1\12\1\16\1\1"
            + "\1\2\1\3\1\uffff\1\4\1\5\7\uffff\1\13\1\14\3\uffff";
    static final String DFA6_specialS =
            "\41\uffff}>";
    static final String[] DFA6_transitionS = {
        "\1\5\1\2\3\uffff\1\1\2\uffff\1\3\3\uffff\1\7\4\uffff\1\4\1\uffff"
        + "\1\6",
        "\1\11\7\uffff\1\12",
        "\1\13\1\uffff\1\14\3\uffff\1\16\5\uffff\1\15",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\17\11\uffff\1\20",
        "\1\22",
        "\1\23\11\uffff\1\24",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\25",
        "",
        "",
        "\1\26",
        "\1\27",
        "\1\30",
        "\1\32\2\uffff\2\32\22\uffff\1\32\76\uffff\1\31",
        "\1\33",
        "\1\32\2\uffff\2\32\22\uffff\1\32\1\uffff\1\35\1\uffff\1\34"
        + "\27\uffff\1\35\4\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
        "\1\36",
        "",
        "",
        "\1\37",
        "\1\40",
        "\1\32\2\uffff\2\32\22\uffff\1\32"
    };
    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }

        public String getDescription() {
            return "318:3: ( 'ifdef' | 'ifndef' | 'if' | 'elif' | 'else' | 'endif' | 'line' | 'undef' WS | 'define' WS | 'exec_macro_expression' | ( 'include' | 'include_next' ) WS f= IDENTIFIER | ( 'include' | 'include_next' ) WS f= INCLUDE_FILE | 'warning' m= MACRO_TEXT | 'error' (m= MACRO_TEXT )? | 'pragma' m= MACRO_TEXT |)";
        }
    }
    static final String DFA8_eotS =
            "\1\1\1\uffff\2\4\1\uffff\1\4\1\uffff\4\4\5\uffff\1\4";
    static final String DFA8_eofS =
            "\21\uffff";
    static final String DFA8_minS =
            "\1\0\1\uffff\1\52\1\12\1\uffff\6\0\3\uffff\3\0";
    static final String DFA8_maxS =
            "\1\uffff\1\uffff\1\52\1\12\1\uffff\1\uffff\1\0\4\uffff\3\uffff\2"
            + "\0\1\uffff";
    static final String DFA8_acceptS =
            "\1\uffff\1\5\2\uffff\1\4\6\uffff\1\1\1\2\1\3\3\uffff";
    static final String DFA8_specialS =
            "\1\3\4\uffff\1\11\1\2\1\4\1\1\1\0\1\5\3\uffff\1\7\1\10\1\6}>";
    static final String[] DFA8_transitionS = {
        "\12\4\1\uffff\44\4\1\2\54\4\1\3\uffa3\4",
        "",
        "\1\5",
        "\1\6",
        "",
        "\12\12\1\13\37\12\1\7\4\12\1\10\54\12\1\11\uffa3\12",
        "\1\uffff",
        "\12\12\1\13\37\12\1\7\4\12\1\16\54\12\1\11\uffa3\12",
        "\12\12\1\13\37\12\1\17\4\12\1\10\54\12\1\11\uffa3\12",
        "\12\12\1\20\37\12\1\7\4\12\1\10\54\12\1\11\uffa3\12",
        "\12\12\1\13\37\12\1\7\4\12\1\10\54\12\1\11\uffa3\12",
        "",
        "",
        "",
        "\1\uffff",
        "\1\uffff",
        "\12\12\1\13\37\12\1\7\4\12\1\10\54\12\1\11\uffa3\12"
    };
    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }

        public String getDescription() {
            return "()+ loopback of 391:17: ( ( '/*' )=> '/*' ( options {greedy=false; } : . )* '*/' | ( '\\\\\\n' )=> ( '\\\\\\n' ) | ( '\\\\\\r\\n' )=> ( '\\\\\\n' ) |~ '\\n' )+";
        }

        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
            int _s = s;
            switch (s) {
                case 0:
                    int LA8_9 = input.LA(1);

                    s = -1;
                    if ((LA8_9 == '\n')) {
                        s = 16;
                    } else if ((LA8_9 == '*')) {
                        s = 7;
                    } else if ((LA8_9 == '/')) {
                        s = 8;
                    } else if ((LA8_9 == '\\')) {
                        s = 9;
                    } else if (((LA8_9 >= '\u0000' && LA8_9 <= '\t') || (LA8_9 >= '\u000B' && LA8_9 <= ')') || (LA8_9 >= '+' && LA8_9 <= '.') || (LA8_9 >= '0' && LA8_9 <= '[') || (LA8_9 >= ']' && LA8_9 <= '\uFFFF'))) {
                        s = 10;
                    } else {
                        s = 4;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 1:
                    int LA8_8 = input.LA(1);


                    int index8_8 = input.index();
                    input.rewind();

                    s = -1;
                    if ((LA8_8 == '*')) {
                        s = 15;
                    } else if ((LA8_8 == '/')) {
                        s = 8;
                    } else if ((LA8_8 == '\\')) {
                        s = 9;
                    } else if (((LA8_8 >= '\u0000' && LA8_8 <= '\t') || (LA8_8 >= '\u000B' && LA8_8 <= ')') || (LA8_8 >= '+' && LA8_8 <= '.') || (LA8_8 >= '0' && LA8_8 <= '[') || (LA8_8 >= ']' && LA8_8 <= '\uFFFF'))) {
                        s = 10;
                    } else if ((LA8_8 == '\n') && (synpred1_Cpp())) {
                        s = 11;
                    } else {
                        s = 4;
                    }


                    input.seek(index8_8);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 2:
                    int LA8_6 = input.LA(1);


                    int index8_6 = input.index();
                    input.rewind();

                    s = -1;
                    if ((synpred2_Cpp())) {
                        s = 12;
                    } else if ((synpred3_Cpp())) {
                        s = 13;
                    }


                    input.seek(index8_6);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 3:
                    int LA8_0 = input.LA(1);

                    s = -1;
                    if ((LA8_0 == '/')) {
                        s = 2;
                    } else if ((LA8_0 == '\\')) {
                        s = 3;
                    } else if (((LA8_0 >= '\u0000' && LA8_0 <= '\t') || (LA8_0 >= '\u000B' && LA8_0 <= '.') || (LA8_0 >= '0' && LA8_0 <= '[') || (LA8_0 >= ']' && LA8_0 <= '\uFFFF'))) {
                        s = 4;
                    } else {
                        s = 1;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 4:
                    int LA8_7 = input.LA(1);


                    int index8_7 = input.index();
                    input.rewind();

                    s = -1;
                    if ((LA8_7 == '/')) {
                        s = 14;
                    } else if ((LA8_7 == '*')) {
                        s = 7;
                    } else if ((LA8_7 == '\\')) {
                        s = 9;
                    } else if (((LA8_7 >= '\u0000' && LA8_7 <= '\t') || (LA8_7 >= '\u000B' && LA8_7 <= ')') || (LA8_7 >= '+' && LA8_7 <= '.') || (LA8_7 >= '0' && LA8_7 <= '[') || (LA8_7 >= ']' && LA8_7 <= '\uFFFF'))) {
                        s = 10;
                    } else if ((LA8_7 == '\n') && (synpred1_Cpp())) {
                        s = 11;
                    } else {
                        s = 4;
                    }


                    input.seek(index8_7);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 5:
                    int LA8_10 = input.LA(1);


                    int index8_10 = input.index();
                    input.rewind();

                    s = -1;
                    if ((LA8_10 == '*')) {
                        s = 7;
                    } else if ((LA8_10 == '/')) {
                        s = 8;
                    } else if ((LA8_10 == '\\')) {
                        s = 9;
                    } else if (((LA8_10 >= '\u0000' && LA8_10 <= '\t') || (LA8_10 >= '\u000B' && LA8_10 <= ')') || (LA8_10 >= '+' && LA8_10 <= '.') || (LA8_10 >= '0' && LA8_10 <= '[') || (LA8_10 >= ']' && LA8_10 <= '\uFFFF'))) {
                        s = 10;
                    } else if ((LA8_10 == '\n') && (synpred1_Cpp())) {
                        s = 11;
                    } else {
                        s = 4;
                    }


                    input.seek(index8_10);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 6:
                    int LA8_16 = input.LA(1);


                    int index8_16 = input.index();
                    input.rewind();

                    s = -1;
                    if ((LA8_16 == '/')) {
                        s = 8;
                    } else if ((LA8_16 == '\\')) {
                        s = 9;
                    } else if ((LA8_16 == '*')) {
                        s = 7;
                    } else if (((LA8_16 >= '\u0000' && LA8_16 <= '\t') || (LA8_16 >= '\u000B' && LA8_16 <= ')') || (LA8_16 >= '+' && LA8_16 <= '.') || (LA8_16 >= '0' && LA8_16 <= '[') || (LA8_16 >= ']' && LA8_16 <= '\uFFFF'))) {
                        s = 10;
                    } else if ((LA8_16 == '\n') && (synpred1_Cpp())) {
                        s = 11;
                    } else {
                        s = 4;
                    }


                    input.seek(index8_16);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 7:
                    int LA8_14 = input.LA(1);


                    int index8_14 = input.index();
                    input.rewind();

                    s = -1;
                    if ((synpred1_Cpp())) {
                        s = 11;
                    } else if ((true)) {
                        s = 4;
                    }


                    input.seek(index8_14);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 8:
                    int LA8_15 = input.LA(1);


                    int index8_15 = input.index();
                    input.rewind();

                    s = -1;
                    if ((synpred1_Cpp())) {
                        s = 11;
                    } else if ((true)) {
                        s = 4;
                    }


                    input.seek(index8_15);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 9:
                    int LA8_5 = input.LA(1);


                    int index8_5 = input.index();
                    input.rewind();

                    s = -1;
                    if ((LA8_5 == '*')) {
                        s = 7;
                    } else if ((LA8_5 == '/')) {
                        s = 8;
                    } else if ((LA8_5 == '\\')) {
                        s = 9;
                    } else if (((LA8_5 >= '\u0000' && LA8_5 <= '\t') || (LA8_5 >= '\u000B' && LA8_5 <= ')') || (LA8_5 >= '+' && LA8_5 <= '.') || (LA8_5 >= '0' && LA8_5 <= '[') || (LA8_5 >= ']' && LA8_5 <= '\uFFFF'))) {
                        s = 10;
                    } else if ((LA8_5 == '\n') && (synpred1_Cpp())) {
                        s = 11;
                    } else {
                        s = 4;
                    }


                    input.seek(index8_5);

                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (backtracking > 0) {
                failed = true;
                return -1;
            }

            NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA34_eotS =
            "\6\uffff";
    static final String DFA34_eofS =
            "\6\uffff";
    static final String DFA34_minS =
            "\2\56\4\uffff";
    static final String DFA34_maxS =
            "\1\71\1\146\4\uffff";
    static final String DFA34_acceptS =
            "\2\uffff\1\2\1\1\1\3\1\4";
    static final String DFA34_specialS =
            "\6\uffff}>";
    static final String[] DFA34_transitionS = {
        "\1\2\1\uffff\12\1",
        "\1\3\1\uffff\12\1\12\uffff\1\5\1\4\1\5\35\uffff\1\5\1\4\1\5",
        "",
        "",
        "",
        ""
    };
    static final short[] DFA34_eot = DFA.unpackEncodedString(DFA34_eotS);
    static final short[] DFA34_eof = DFA.unpackEncodedString(DFA34_eofS);
    static final char[] DFA34_min = DFA.unpackEncodedStringToUnsignedChars(DFA34_minS);
    static final char[] DFA34_max = DFA.unpackEncodedStringToUnsignedChars(DFA34_maxS);
    static final short[] DFA34_accept = DFA.unpackEncodedString(DFA34_acceptS);
    static final short[] DFA34_special = DFA.unpackEncodedString(DFA34_specialS);
    static final short[][] DFA34_transition;

    static {
        int numStates = DFA34_transitionS.length;
        DFA34_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA34_transition[i] = DFA.unpackEncodedString(DFA34_transitionS[i]);
        }
    }

    class DFA34 extends DFA {

        public DFA34(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 34;
            this.eot = DFA34_eot;
            this.eof = DFA34_eof;
            this.min = DFA34_min;
            this.max = DFA34_max;
            this.accept = DFA34_accept;
            this.special = DFA34_special;
            this.transition = DFA34_transition;
        }

        public String getDescription() {
            return "591:1: FLOATING_POINT_LITERAL : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix );";
        }
    }
    static final String DFA48_eotS =
            "\1\uffff\1\26\1\uffff\1\32\4\uffff\1\34\1\36\1\40\1\43\1\46\1\50"
            + "\1\53\1\55\1\60\1\63\1\65\1\70\3\uffff\1\72\12\uffff\1\74\2\uffff"
            + "\1\76\31\uffff";
    static final String DFA48_eofS =
            "\77\uffff";
    static final String DFA48_minS =
            "\1\41\1\72\1\uffff\1\55\4\uffff\3\75\1\74\2\75\1\53\1\75\1\46\2"
            + "\75\1\52\3\uffff\1\52\12\uffff\1\75\2\uffff\1\75\31\uffff";
    static final String DFA48_maxS =
            "\1\176\1\72\1\uffff\1\76\4\uffff\4\75\1\76\4\75\1\174\1\75\1\56"
            + "\3\uffff\1\52\12\uffff\1\75\2\uffff\1\75\31\uffff";
    static final String DFA48_acceptS =
            "\2\uffff\1\2\1\uffff\1\4\1\5\1\6\1\7\14\uffff\1\40\1\34\1\1\1\uffff"
            + "\1\21\1\46\1\37\1\42\1\10\1\11\1\41\1\12\1\54\1\13\1\uffff\1\14"
            + "\1\15\1\uffff\1\16\1\43\1\17\1\20\1\45\1\36\1\44\1\22\1\25\1\51"
            + "\1\35\1\26\1\53\1\27\1\52\1\30\1\33\1\55\1\31\1\32\1\3\1\47\1\24"
            + "\1\50\1\23";
    static final String DFA48_specialS =
            "\77\uffff}>";
    static final String[] DFA48_transitionS = {
        "\1\12\3\uffff\1\17\1\20\3\uffff\1\10\1\16\1\uffff\1\3\1\23\1"
        + "\15\12\uffff\1\1\1\uffff\1\13\1\11\1\14\1\2\33\uffff\1\6\1\uffff"
        + "\1\7\1\22\34\uffff\1\4\1\21\1\5\1\24",
        "\1\25",
        "",
        "\1\30\17\uffff\1\31\1\27",
        "",
        "",
        "",
        "",
        "\1\33",
        "\1\35",
        "\1\37",
        "\1\42\1\41",
        "\1\44\1\45",
        "\1\47",
        "\1\51\21\uffff\1\52",
        "\1\54",
        "\1\56\26\uffff\1\57",
        "\1\62\76\uffff\1\61",
        "\1\64",
        "\1\66\3\uffff\1\67",
        "",
        "",
        "",
        "\1\71",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\73",
        "",
        "",
        "\1\75",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    };
    static final short[] DFA48_eot = DFA.unpackEncodedString(DFA48_eotS);
    static final short[] DFA48_eof = DFA.unpackEncodedString(DFA48_eofS);
    static final char[] DFA48_min = DFA.unpackEncodedStringToUnsignedChars(DFA48_minS);
    static final char[] DFA48_max = DFA.unpackEncodedStringToUnsignedChars(DFA48_maxS);
    static final short[] DFA48_accept = DFA.unpackEncodedString(DFA48_acceptS);
    static final short[] DFA48_special = DFA.unpackEncodedString(DFA48_specialS);
    static final short[][] DFA48_transition;

    static {
        int numStates = DFA48_transitionS.length;
        DFA48_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA48_transition[i] = DFA.unpackEncodedString(DFA48_transitionS[i]);
        }
    }

    class DFA48 extends DFA {

        public DFA48(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 48;
            this.eot = DFA48_eot;
            this.eof = DFA48_eof;
            this.min = DFA48_min;
            this.max = DFA48_max;
            this.accept = DFA48_accept;
            this.special = DFA48_special;
            this.transition = DFA48_transition;
        }

        public String getDescription() {
            return "758:9: ( COLON | QUESTIONMARK | POINTERTO | LCURLY | RCURLY | LSQUARE | RSQUARE | STAR | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | PLUSPLUS | MINUSMINUS | MOD | SHIFTRIGHT | SHIFTLEFT | AND | OR | BITWISEOR | BITWISEXOR | DOT | POINTERTOMBR | DOTMBR | SCOPE | AMPERSAND | PLUS | MINUS | TILDE | ASSIGNEQUAL | TIMESEQUAL | DIVIDEEQUAL | MODEQUAL | PLUSEQUAL | MINUSEQUAL | SHIFTLEFTEQUAL | SHIFTRIGHTEQUAL | BITWISEANDEQUAL | BITWISEXOREQUAL | BITWISEOREQUAL | NOT | ELLIPSIS )";
        }
    }
    static final String DFA50_eotS =
            "\1\uffff\1\76\3\62\1\106\1\110\1\uffff\1\111\1\uffff\1\115\2\uffff"
            + "\1\116\1\117\1\120\1\121\1\123\1\126\1\131\1\135\1\140\1\142\1\144"
            + "\1\147\1\152\1\153\1\155\1\160\1\62\2\uffff\2\164\1\167\2\uffff"
            + "\15\62\1\uffff\1\75\1\uffff\1\76\7\75\2\uffff\6\62\1\u009f\1\uffff"
            + "\1\u00a2\2\uffff\1\u00a6\1\u00a7\1\u00a8\5\uffff\1\u00ae\1\uffff"
            + "\1\u00b0\1\u00b2\1\uffff\1\u00b4\1\u00b6\1\uffff\1\u00b8\3\uffff"
            + "\1\u00ba\1\u00bb\1\uffff\1\u00bd\1\uffff\1\u00bf\1\uffff\1\u00c1"
            + "\1\u00c2\1\uffff\1\u00c4\1\u00c5\2\uffff\1\u00c8\1\uffff\1\u00ca"
            + "\3\uffff\1\62\2\uffff\1\u00ce\1\164\1\uffff\13\62\1\uffff\7\62\1"
            + "\uffff\1\u00e7\12\75\10\62\6\uffff\1\u00fe\13\uffff\1\u0104\3\uffff"
            + "\1\u0107\25\uffff\1\u0114\1\uffff\1\62\1\uffff\15\62\1\uffff\2\62"
            + "\1\uffff\5\62\2\75\1\uffff\13\75\11\62\31\uffff\5\62\3\uffff\2\62"
            + "\1\uffff\1\62\1\uffff\2\62\2\uffff\4\62\1\uffff\1\62\3\75\2\u00e7"
            + "\3\75\1\u00e7\4\75\4\62\1\uffff\4\62\4\uffff\10\62\1\uffff\1\62"
            + "\1\uffff\1\62\3\uffff\1\u00e7\2\75\1\u00e7\1\75\1\u00e7\4\75\1\u0178"
            + "\4\uffff\2\62\1\uffff\4\62\1\uffff\1\62\1\uffff\3\62\1\u00e7\2\75"
            + "\1\u00e7\3\75\1\uffff\1\u0187\1\uffff\1\u0188\1\62\1\uffff\5\62"
            + "\3\75\1\u00e7\2\uffff\2\62\4\uffff\2\75\1\u00e7\1\uffff\1\62\1\uffff"
            + "\2\75\1\62\2\75\1\uffff\14\75\1\u00e7";
    static final String DFA50_eofS =
            "\u01a8\uffff";
    static final String DFA50_minS =
            "\2\11\1\150\1\145\1\47\1\75\1\72\1\uffff\1\0\1\uffff\1\55\2\uffff"
            + "\4\0\1\75\1\74\1\75\1\52\1\53\2\75\1\46\1\75\1\0\1\75\1\52\1\171"
            + "\2\uffff\2\56\1\11\2\uffff\1\137\1\154\1\165\1\145\1\157\1\141\1"
            + "\146\1\157\1\154\1\156\1\150\1\157\1\162\1\uffff\1\43\1\uffff\1"
            + "\11\1\146\1\154\1\151\1\156\1\145\1\141\1\162\2\uffff\1\147\1\141"
            + "\1\157\1\151\1\146\1\165\5\0\1\52\12\0\1\75\2\0\1\75\2\0\2\uffff"
            + "\22\0\1\56\1\0\1\uffff\1\160\2\uffff\2\56\1\uffff\1\146\1\164\1"
            + "\165\1\163\1\164\1\147\1\151\1\141\1\156\1\163\1\164\1\uffff\1\156"
            + "\1\157\1\162\2\151\1\164\1\145\1\uffff\1\44\1\143\1\151\1\144\1"
            + "\145\1\162\1\156\1\144\1\146\1\162\1\141\1\145\1\156\1\164\1\165"
            + "\1\162\1\164\1\141\1\142\1\0\2\uffff\1\0\2\uffff\4\0\5\uffff\1\0"
            + "\1\uffff\3\0\1\uffff\3\0\1\uffff\1\0\1\uffff\2\0\1\uffff\1\0\1\uffff"
            + "\1\0\1\uffff\2\0\1\uffff\2\0\2\uffff\1\0\1\uffff\2\0\1\uffff\1\145"
            + "\1\uffff\1\141\1\154\1\145\1\155\1\145\1\157\1\151\1\165\1\144\1"
            + "\141\1\162\1\163\1\145\1\uffff\1\147\1\141\1\uffff\1\151\1\157\1"
            + "\154\1\157\1\141\1\145\1\144\1\0\1\154\1\146\1\145\1\151\1\143\1"
            + "\157\2\145\1\151\1\156\1\147\1\157\1\145\1\151\1\143\1\164\1\143"
            + "\1\156\1\165\1\154\2\uffff\1\0\5\uffff\1\0\2\uffff\1\0\14\uffff"
            + "\1\0\1\144\1\137\1\162\1\157\1\162\3\uffff\1\163\1\162\1\uffff\1"
            + "\164\1\uffff\1\164\1\151\2\uffff\1\164\1\147\1\156\1\145\1\uffff"
            + "\1\153\1\146\1\145\1\165\2\44\1\146\1\137\1\162\1\44\1\146\1\156"
            + "\1\151\1\155\1\146\1\144\1\143\1\164\1\uffff\1\150\1\145\1\154\1"
            + "\145\4\uffff\1\145\1\154\1\145\1\141\1\156\1\164\1\156\1\151\1\uffff"
            + "\1\156\1\uffff\1\156\3\uffff\1\44\1\146\1\144\1\44\1\155\1\0\1\11"
            + "\1\145\1\156\1\141\1\44\4\uffff\1\144\1\164\1\uffff\1\146\1\151"
            + "\1\147\1\164\1\uffff\1\145\1\uffff\1\154\1\165\1\145\1\44\1\145"
            + "\1\141\1\0\1\11\1\147\1\0\1\uffff\1\44\1\uffff\1\44\1\163\1\uffff"
            + "\1\70\1\162\2\145\1\144\1\11\1\143\2\0\1\uffff\1\0\1\164\1\60\4"
            + "\uffff\1\156\1\162\1\0\1\uffff\1\137\1\uffff\1\145\1\157\1\137\1"
            + "\170\1\137\1\uffff\1\164\1\145\1\11\1\170\1\160\1\162\1\145\2\163"
            + "\1\151\1\157\1\156\1\44";
    static final String DFA50_maxS =
            "\1\176\1\172\1\167\1\157\1\47\1\75\1\72\1\uffff\1\0\1\uffff\1\76"
            + "\2\uffff\4\0\2\75\1\76\1\134\4\75\1\174\1\0\1\75\1\71\1\171\2\uffff"
            + "\1\170\1\146\1\40\2\uffff\1\137\1\170\1\165\1\145\2\157\1\156\2"
            + "\157\1\156\1\150\1\157\1\162\1\uffff\1\43\1\uffff\1\172\1\156\1"
            + "\170\1\151\1\156\1\145\1\141\1\162\2\uffff\1\172\1\162\1\157\1\151"
            + "\1\146\1\165\5\0\1\52\12\0\1\75\2\0\1\75\2\0\2\uffff\22\0\1\56\1"
            + "\0\1\uffff\1\160\2\uffff\2\146\1\uffff\1\166\1\164\1\165\1\163\2"
            + "\164\1\154\1\141\1\156\1\163\1\164\1\uffff\1\156\1\157\1\162\1\163"
            + "\1\151\1\164\1\145\1\uffff\1\172\1\143\1\163\1\144\1\145\1\162\1"
            + "\156\1\144\1\146\1\162\1\141\1\145\1\156\1\164\1\165\1\162\1\164"
            + "\1\151\1\142\1\0\2\uffff\1\0\2\uffff\4\0\5\uffff\1\0\1\uffff\3\0"
            + "\1\uffff\3\0\1\uffff\1\0\1\uffff\2\0\1\uffff\1\0\1\uffff\1\0\1\uffff"
            + "\2\0\1\uffff\2\0\2\uffff\1\0\1\uffff\2\0\1\uffff\1\145\1\uffff\1"
            + "\141\1\160\1\145\1\155\1\145\1\157\1\151\1\165\1\144\1\141\1\162"
            + "\1\164\1\145\1\uffff\1\147\1\141\1\uffff\1\151\1\157\1\154\1\157"
            + "\1\141\1\145\1\144\1\0\1\154\1\146\1\145\1\151\1\143\1\157\2\145"
            + "\1\151\1\156\1\147\1\157\1\145\1\151\1\143\1\164\1\143\1\156\1\165"
            + "\1\154\2\uffff\1\0\5\uffff\1\0\2\uffff\1\0\14\uffff\1\0\1\144\1"
            + "\137\1\162\1\157\1\162\3\uffff\1\163\1\162\1\uffff\1\164\1\uffff"
            + "\1\164\1\151\2\uffff\1\164\1\147\1\156\1\145\1\uffff\1\153\1\146"
            + "\1\145\1\165\2\172\1\146\1\137\1\162\1\172\1\146\1\156\1\151\1\155"
            + "\1\146\1\144\1\143\1\164\1\uffff\1\150\1\145\1\154\1\145\4\uffff"
            + "\1\145\1\154\1\145\1\141\1\156\1\164\1\156\1\151\1\uffff\1\156\1"
            + "\uffff\1\156\3\uffff\1\172\1\146\1\144\1\172\1\155\1\uffff\1\40"
            + "\1\145\1\156\1\141\1\172\4\uffff\1\144\1\164\1\uffff\1\146\1\151"
            + "\1\147\1\164\1\uffff\1\145\1\uffff\1\154\1\165\1\145\1\172\1\145"
            + "\1\141\1\uffff\1\40\1\147\1\uffff\1\uffff\1\172\1\uffff\1\172\1"
            + "\163\1\uffff\1\70\1\162\2\145\1\144\1\137\1\143\2\uffff\1\uffff"
            + "\1\0\1\164\1\60\4\uffff\1\156\1\162\1\uffff\1\uffff\1\137\1\uffff"
            + "\1\145\1\157\1\137\1\170\1\137\1\uffff\1\164\1\145\1\40\1\170\1"
            + "\160\1\162\1\145\2\163\1\151\1\157\1\156\1\172";
    static final String DFA50_acceptS =
            "\7\uffff\1\12\1\uffff\1\14\1\uffff\1\16\1\17\21\uffff\1\71\1\72"
            + "\3\uffff\1\102\1\103\15\uffff\1\5\1\uffff\1\7\10\uffff\1\1\1\2\34"
            + "\uffff\1\77\1\100\24\uffff\1\76\1\uffff\1\73\1\74\2\uffff\1\101"
            + "\13\uffff\1\5\7\uffff\1\6\24\uffff\1\10\1\104\1\uffff\1\11\1\13"
            + "\4\uffff\1\37\1\20\1\21\1\22\1\23\1\uffff\1\53\3\uffff\1\27\3\uffff"
            + "\1\31\1\uffff\1\32\2\uffff\1\34\1\uffff\1\42\1\uffff\1\44\2\uffff"
            + "\1\55\2\uffff\1\60\1\57\1\uffff\1\62\2\uffff\1\64\1\uffff\1\75\15"
            + "\uffff\1\5\2\uffff\1\5\34\uffff\1\24\1\67\1\uffff\1\15\1\40\1\41"
            + "\1\25\1\26\1\uffff\1\50\1\30\1\uffff\1\46\1\33\1\35\1\36\1\43\1"
            + "\45\1\52\1\56\1\54\1\61\1\63\1\66\6\uffff\3\5\2\uffff\1\5\1\uffff"
            + "\1\5\2\uffff\2\5\4\uffff\1\5\22\uffff\1\5\4\uffff\1\65\1\51\1\47"
            + "\1\70\10\uffff\1\5\1\uffff\1\5\1\uffff\3\5\13\uffff\4\5\2\uffff"
            + "\1\5\4\uffff\1\5\1\uffff\1\5\12\uffff\1\3\1\uffff\1\5\2\uffff\1"
            + "\5\11\uffff\1\4\3\uffff\4\5\3\uffff\1\105\1\uffff\1\5\5\uffff\1"
            + "\5\15\uffff";
    static final String DFA50_specialS =
            "\106\uffff\1\4\1\uffff\1\3\1\11\3\uffff\1\22\1\6\1\7\1\20\1\21\1"
            + "\uffff\1\47\2\uffff\1\15\2\uffff\1\13\3\uffff\1\27\2\uffff\1\31"
            + "\1\uffff\1\37\1\uffff\1\41\2\uffff\1\51\2\uffff\1\44\1\43\1\uffff"
            + "\1\55\2\uffff\1\57\56\uffff\1\16\2\uffff\1\53\3\uffff\1\10\1\25"
            + "\1\24\5\uffff\1\17\1\uffff\1\14\1\uffff\1\35\1\uffff\1\12\1\uffff"
            + "\1\33\1\uffff\1\26\1\uffff\1\30\1\23\1\uffff\1\36\1\uffff\1\40\1"
            + "\uffff\1\46\1\42\1\uffff\1\50\1\45\2\uffff\1\56\1\uffff\1\52\34"
            + "\uffff\1\61\26\uffff\1\60\5\uffff\1\34\2\uffff\1\32\14\uffff\1\54"
            + "\105\uffff\1\62\31\uffff\1\2\2\uffff\1\0\15\uffff\1\1\1\63\1\uffff"
            + "\1\5\10\uffff\1\64\26\uffff}>";
    static final String[] DFA50_transitionS = {
        "\1\42\1\43\1\uffff\2\42\22\uffff\1\42\1\21\1\37\1\1\1\62\1\27"
        + "\1\30\1\36\1\13\1\14\1\26\1\25\1\7\1\12\1\34\1\24\1\40\11\41"
        + "\1\6\1\11\1\22\1\5\1\23\1\10\1\uffff\13\62\1\4\16\62\1\15\1"
        + "\44\1\16\1\33\1\45\1\uffff\1\47\1\61\1\52\1\3\1\46\1\55\1\60"
        + "\1\62\1\53\2\62\1\54\5\62\1\50\1\2\1\35\1\56\1\51\1\57\3\62"
        + "\1\17\1\31\1\20\1\32",
        "\1\65\2\uffff\2\65\22\uffff\1\65\2\uffff\1\64\1\75\34\uffff"
        + "\32\75\4\uffff\1\63\1\uffff\3\75\1\72\1\67\3\75\1\66\2\75\1"
        + "\70\3\75\1\74\4\75\1\71\1\75\1\73\3\75",
        "\1\101\1\77\12\uffff\1\100\2\uffff\1\102",
        "\1\103\11\uffff\1\104",
        "\1\36",
        "\1\105",
        "\1\107",
        "",
        "\1\uffff",
        "",
        "\1\114\17\uffff\1\113\1\112",
        "",
        "",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\122",
        "\1\125\1\124",
        "\1\127\1\130",
        "\1\133\4\uffff\1\134\15\uffff\1\132\36\uffff\1\133",
        "\1\137\21\uffff\1\136",
        "\1\141",
        "\1\143",
        "\1\145\26\uffff\1\146",
        "\1\151\76\uffff\1\150",
        "\1\uffff",
        "\1\154",
        "\1\156\3\uffff\1\157\1\uffff\12\161",
        "\1\162",
        "",
        "",
        "\1\161\1\uffff\10\165\2\161\12\uffff\3\161\21\uffff\1\163\13"
        + "\uffff\3\161\21\uffff\1\163",
        "\1\161\1\uffff\12\166\12\uffff\3\161\35\uffff\3\161",
        "\1\42\1\43\1\uffff\2\42\22\uffff\1\42",
        "",
        "",
        "\1\170",
        "\1\173\1\uffff\1\172\11\uffff\1\171",
        "\1\174",
        "\1\175",
        "\1\176",
        "\1\u0081\6\uffff\1\177\6\uffff\1\u0080",
        "\1\u0083\7\uffff\1\u0082",
        "\1\u0084",
        "\1\u0085\2\uffff\1\u0086",
        "\1\u0087",
        "\1\u0088",
        "\1\u0089",
        "\1\u008a",
        "",
        "\1\u008b",
        "",
        "\1\65\2\uffff\2\65\22\uffff\1\65\3\uffff\1\75\34\uffff\32\75"
        + "\4\uffff\1\75\1\uffff\3\75\1\72\1\67\3\75\1\66\2\75\1\70\3\75"
        + "\1\74\4\75\1\71\1\75\1\73\3\75",
        "\1\u008c\7\uffff\1\u008d",
        "\1\u008e\1\uffff\1\u008f\3\uffff\1\u0091\5\uffff\1\u0090",
        "\1\u0092",
        "\1\u0093",
        "\1\u0094",
        "\1\u0095",
        "\1\u0096",
        "",
        "",
        "\1\u0098\22\uffff\1\u0097",
        "\1\u0099\20\uffff\1\u009a",
        "\1\u009b",
        "\1\u009c",
        "\1\u009d",
        "\1\u009e",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\u00a5",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\u00b1",
        "\1\uffff",
        "\1\uffff",
        "\1\u00b5",
        "\1\uffff",
        "\1\uffff",
        "",
        "",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\u00cb",
        "\1\uffff",
        "",
        "\1\u00cd",
        "",
        "",
        "\1\161\1\uffff\10\165\2\161\12\uffff\3\161\35\uffff\3\161",
        "\1\161\1\uffff\12\166\12\uffff\3\161\35\uffff\3\161",
        "",
        "\1\u00d0\17\uffff\1\u00cf",
        "\1\u00d1",
        "\1\u00d2",
        "\1\u00d3",
        "\1\u00d4",
        "\1\u00d5\14\uffff\1\u00d6",
        "\1\u00d7\2\uffff\1\u00d8",
        "\1\u00d9",
        "\1\u00da",
        "\1\u00db",
        "\1\u00dc",
        "",
        "\1\u00dd",
        "\1\u00de",
        "\1\u00df",
        "\1\u00e1\11\uffff\1\u00e0",
        "\1\u00e2",
        "\1\u00e3",
        "\1\u00e4",
        "",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\3\75"
        + "\1\u00e5\11\75\1\u00e6\14\75",
        "\1\u00e8",
        "\1\u00e9\11\uffff\1\u00ea",
        "\1\u00eb",
        "\1\u00ec",
        "\1\u00ed",
        "\1\u00ee",
        "\1\u00ef",
        "\1\u00f0",
        "\1\u00f1",
        "\1\u00f2",
        "\1\u00f3",
        "\1\u00f4",
        "\1\u00f5",
        "\1\u00f6",
        "\1\u00f7",
        "\1\u00f8",
        "\1\u00fa\7\uffff\1\u00f9",
        "\1\u00fb",
        "\1\uffff",
        "",
        "",
        "\1\uffff",
        "",
        "",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "",
        "",
        "",
        "",
        "",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "\1\uffff",
        "",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "",
        "\1\uffff",
        "",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "",
        "",
        "\1\uffff",
        "",
        "\1\uffff",
        "\1\uffff",
        "",
        "\1\u0115",
        "",
        "\1\u0116",
        "\1\u0118\3\uffff\1\u0117",
        "\1\u0119",
        "\1\u011a",
        "\1\u011b",
        "\1\u011c",
        "\1\u011d",
        "\1\u011e",
        "\1\u011f",
        "\1\u0120",
        "\1\u0121",
        "\1\u0122\1\u0123",
        "\1\u0124",
        "",
        "\1\u0125",
        "\1\u0126",
        "",
        "\1\u0127",
        "\1\u0128",
        "\1\u0129",
        "\1\u012a",
        "\1\u012b",
        "\1\u012c",
        "\1\u012d",
        "\1\uffff",
        "\1\u012e",
        "\1\u012f",
        "\1\u0130",
        "\1\u0131",
        "\1\u0132",
        "\1\u0133",
        "\1\u0134",
        "\1\u0135",
        "\1\u0136",
        "\1\u0137",
        "\1\u0138",
        "\1\u0139",
        "\1\u013a",
        "\1\u013b",
        "\1\u013c",
        "\1\u013d",
        "\1\u013e",
        "\1\u013f",
        "\1\u0140",
        "\1\u0141",
        "",
        "",
        "\1\uffff",
        "",
        "",
        "",
        "",
        "",
        "\1\uffff",
        "",
        "",
        "\1\uffff",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "\1\uffff",
        "\1\u0146",
        "\1\u0147",
        "\1\u0148",
        "\1\u0149",
        "\1\u014a",
        "",
        "",
        "",
        "\1\u014b",
        "\1\u014c",
        "",
        "\1\u014d",
        "",
        "\1\u014e",
        "\1\u014f",
        "",
        "",
        "\1\u0150",
        "\1\u0151",
        "\1\u0152",
        "\1\u0153",
        "",
        "\1\u0154",
        "\1\u0155",
        "\1\u0156",
        "\1\u0157",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\u0158",
        "\1\u0159",
        "\1\u015a",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\u015b",
        "\1\u015c",
        "\1\u015d",
        "\1\u015e",
        "\1\u015f",
        "\1\u0160",
        "\1\u0161",
        "\1\u0162",
        "",
        "\1\u0163",
        "\1\u0164",
        "\1\u0165",
        "\1\u0166",
        "",
        "",
        "",
        "",
        "\1\u0167",
        "\1\u0168",
        "\1\u0169",
        "\1\u016a",
        "\1\u016b",
        "\1\u016c",
        "\1\u016d",
        "\1\u016e",
        "",
        "\1\u016f",
        "",
        "\1\u0170",
        "",
        "",
        "",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\u0171",
        "\1\u0172",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\u0173",
        "\12\76\1\uffff\31\76\1\u0174\13\76\12\u0174\7\76\32\u0174\4"
        + "\76\1\u0174\1\76\32\u0174\uff85\76",
        "\1\76\2\uffff\2\76\22\uffff\1\76",
        "\1\u0175",
        "\1\u0176",
        "\1\u0177",
        "\1\62\13\uffff\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32"
        + "\62",
        "",
        "",
        "",
        "",
        "\1\u0179",
        "\1\u017a",
        "",
        "\1\u017b",
        "\1\u017c",
        "\1\u017d",
        "\1\u017e",
        "",
        "\1\u017f",
        "",
        "\1\u0180",
        "\1\u0181",
        "\1\u0182",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75",
        "\1\u0183",
        "\1\u0184",
        "\12\76\1\uffff\31\76\1\u0174\13\76\12\u0174\7\76\32\u0174\4"
        + "\76\1\u0174\1\76\32\u0174\uff85\76",
        "\1\76\2\uffff\2\76\22\uffff\1\76",
        "\1\u0185",
        "\12\76\1\uffff\31\76\1\u0186\13\76\12\u0186\7\76\32\u0186\4"
        + "\76\1\u0186\1\76\32\u0186\uff85\76",
        "",
        "\1\62\13\uffff\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32"
        + "\62",
        "",
        "\1\62\13\uffff\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32"
        + "\62",
        "\1\u0189",
        "",
        "\1\u018a",
        "\1\u018b",
        "\1\u018c",
        "\1\u018d",
        "\1\u018e",
        "\1\76\2\uffff\2\76\22\uffff\1\76\76\uffff\1\u018f",
        "\1\u0190",
        "\12\76\1\uffff\31\76\1\u0191\13\76\12\u0191\7\76\32\u0191\4"
        + "\76\1\u0191\1\76\32\u0191\uff85\76",
        "\12\76\1\uffff\31\76\1\u0186\13\76\12\u0186\7\76\32\u0186\4"
        + "\76\1\u0186\1\76\32\u0186\uff85\76",
        "",
        "\1\uffff",
        "\1\u0193",
        "\1\u0194",
        "",
        "",
        "",
        "",
        "\1\u0195",
        "\1\u0196",
        "\12\76\1\uffff\31\76\1\u0191\13\76\12\u0191\7\76\32\u0191\4"
        + "\76\1\u0191\1\76\32\u0191\uff85\76",
        "",
        "\1\u0197",
        "",
        "\1\u0198",
        "\1\u0199",
        "\1\u019a",
        "\1\u019b",
        "\1\u019c",
        "",
        "\1\u019d",
        "\1\u019e",
        "\1\76\2\uffff\2\76\22\uffff\1\76",
        "\1\u019f",
        "\1\u01a0",
        "\1\u01a1",
        "\1\u01a2",
        "\1\u01a3",
        "\1\u01a4",
        "\1\u01a5",
        "\1\u01a6",
        "\1\u01a7",
        "\1\75\13\uffff\12\75\7\uffff\32\75\4\uffff\1\75\1\uffff\32"
        + "\75"
    };
    static final short[] DFA50_eot = DFA.unpackEncodedString(DFA50_eotS);
    static final short[] DFA50_eof = DFA.unpackEncodedString(DFA50_eofS);
    static final char[] DFA50_min = DFA.unpackEncodedStringToUnsignedChars(DFA50_minS);
    static final char[] DFA50_max = DFA.unpackEncodedStringToUnsignedChars(DFA50_maxS);
    static final short[] DFA50_accept = DFA.unpackEncodedString(DFA50_acceptS);
    static final short[] DFA50_special = DFA.unpackEncodedString(DFA50_specialS);
    static final short[][] DFA50_transition;

    static {
        int numStates = DFA50_transitionS.length;
        DFA50_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA50_transition[i] = DFA.unpackEncodedString(DFA50_transitionS[i]);
        }
    }

    class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = DFA50_eot;
            this.eof = DFA50_eof;
            this.min = DFA50_min;
            this.max = DFA50_max;
            this.accept = DFA50_accept;
            this.special = DFA50_special;
            this.transition = DFA50_transition;
        }

        public String getDescription() {
            return "1:1: Tokens : ( STRING_OP | DIRECTIVE | SIZEOF | DEFINED | IDENTIFIER | STRINGIFICATION | SHARPSHARP | ASSIGNEQUAL | COLON | COMMA | QUESTIONMARK | SEMICOLON | POINTERTO | LPAREN | RPAREN | LSQUARE | RSQUARE | LCURLY | RCURLY | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | DIVIDE | DIVIDEEQUAL | PLUS | PLUSEQUAL | PLUSPLUS | MINUS | MINUSEQUAL | MINUSMINUS | STAR | TIMESEQUAL | MOD | MODEQUAL | SHIFTRIGHT | SHIFTRIGHTEQUAL | SHIFTLEFT | SHIFTLEFTEQUAL | AND | NOT | OR | AMPERSAND | BITWISEANDEQUAL | TILDE | BITWISEOR | BITWISEOREQUAL | BITWISEXOR | BITWISEXOREQUAL | DOT | POINTERTOMBR | DOTMBR | SCOPE | ELLIPSIS | CHARACTER_LITERAL | STRING_LITERAL | HEX_LITERAL | DECIMAL_LITERAL | OCTAL_LITERAL | FLOATING_POINT_LITERAL | COMMENT | LINE_COMMENT | WS | End | ESCAPED_NEWLINE | COPERATOR | CKEYWORD );";
        }

        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
            int _s = s;
            switch (s) {
                case 0:
                    int LA50_375 = input.LA(1);

                    s = -1;
                    if (((LA50_375 >= '\u0000' && LA50_375 <= '\t') || (LA50_375 >= '\u000B' && LA50_375 <= '#') || (LA50_375 >= '%' && LA50_375 <= '/') || (LA50_375 >= ':' && LA50_375 <= '@') || (LA50_375 >= '[' && LA50_375 <= '^') || LA50_375 == '`' || (LA50_375 >= '{' && LA50_375 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_375 == '$' || (LA50_375 >= '0' && LA50_375 <= '9') || (LA50_375 >= 'A' && LA50_375 <= 'Z') || LA50_375 == '_' || (LA50_375 >= 'a' && LA50_375 <= 'z'))) {
                        s = 390;
                    } else {
                        s = 61;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 1:
                    int LA50_389 = input.LA(1);

                    s = -1;
                    if (((LA50_389 >= '\u0000' && LA50_389 <= '\t') || (LA50_389 >= '\u000B' && LA50_389 <= '#') || (LA50_389 >= '%' && LA50_389 <= '/') || (LA50_389 >= ':' && LA50_389 <= '@') || (LA50_389 >= '[' && LA50_389 <= '^') || LA50_389 == '`' || (LA50_389 >= '{' && LA50_389 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_389 == '$' || (LA50_389 >= '0' && LA50_389 <= '9') || (LA50_389 >= 'A' && LA50_389 <= 'Z') || LA50_389 == '_' || (LA50_389 >= 'a' && LA50_389 <= 'z'))) {
                        s = 401;
                    } else {
                        s = 61;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 2:
                    int LA50_372 = input.LA(1);

                    s = -1;
                    if (((LA50_372 >= '\u0000' && LA50_372 <= '\t') || (LA50_372 >= '\u000B' && LA50_372 <= '#') || (LA50_372 >= '%' && LA50_372 <= '/') || (LA50_372 >= ':' && LA50_372 <= '@') || (LA50_372 >= '[' && LA50_372 <= '^') || LA50_372 == '`' || (LA50_372 >= '{' && LA50_372 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_372 == '$' || (LA50_372 >= '0' && LA50_372 <= '9') || (LA50_372 >= 'A' && LA50_372 <= 'Z') || LA50_372 == '_' || (LA50_372 >= 'a' && LA50_372 <= 'z'))) {
                        s = 372;
                    } else {
                        s = 231;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 3:
                    int LA50_72 = input.LA(1);


                    int index50_72 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 163;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_72);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 4:
                    int LA50_70 = input.LA(1);


                    int index50_70 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 160;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_70);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 5:
                    int LA50_392 = input.LA(1);


                    int index50_392 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 50;
                    } else if (((!inDirective))) {
                        s = 402;
                    }


                    input.seek(index50_392);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 6:
                    int LA50_78 = input.LA(1);


                    int index50_78 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 170;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_78);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 7:
                    int LA50_79 = input.LA(1);


                    int index50_79 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 171;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_79);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 8:
                    int LA50_166 = input.LA(1);


                    int index50_166 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 255;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_166);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 9:
                    int LA50_73 = input.LA(1);


                    int index50_73 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 164;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_73);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 10:
                    int LA50_180 = input.LA(1);


                    int index50_180 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 262;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_180);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 11:
                    int LA50_89 = input.LA(1);


                    int index50_89 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 183;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_89);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 12:
                    int LA50_176 = input.LA(1);


                    int index50_176 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 259;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_176);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 13:
                    int LA50_86 = input.LA(1);


                    int index50_86 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 179;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_86);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 14:
                    int LA50_159 = input.LA(1);


                    int index50_159 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 252;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_159);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 15:
                    int LA50_174 = input.LA(1);


                    int index50_174 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 258;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_174);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 16:
                    int LA50_80 = input.LA(1);


                    int index50_80 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 172;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_80);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 17:
                    int LA50_81 = input.LA(1);


                    int index50_81 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 173;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_81);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 18:
                    int LA50_77 = input.LA(1);


                    int index50_77 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 169;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_77);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 19:
                    int LA50_187 = input.LA(1);


                    int index50_187 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 267;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_187);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 20:
                    int LA50_168 = input.LA(1);


                    int index50_168 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 257;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_168);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 21:
                    int LA50_167 = input.LA(1);


                    int index50_167 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 256;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_167);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 22:
                    int LA50_184 = input.LA(1);


                    int index50_184 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 265;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_184);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 23:
                    int LA50_93 = input.LA(1);


                    int index50_93 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 185;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_93);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 24:
                    int LA50_186 = input.LA(1);


                    int index50_186 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 266;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_186);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 25:
                    int LA50_96 = input.LA(1);


                    int index50_96 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 188;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_96);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 26:
                    int LA50_263 = input.LA(1);


                    int index50_263 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 324;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_263);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 27:
                    int LA50_182 = input.LA(1);


                    int index50_182 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 264;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_182);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 28:
                    int LA50_260 = input.LA(1);


                    int index50_260 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 323;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_260);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 29:
                    int LA50_178 = input.LA(1);


                    int index50_178 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 261;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_178);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 30:
                    int LA50_189 = input.LA(1);


                    int index50_189 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 268;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_189);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 31:
                    int LA50_98 = input.LA(1);


                    int index50_98 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 190;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_98);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 32:
                    int LA50_191 = input.LA(1);


                    int index50_191 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 269;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_191);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 33:
                    int LA50_100 = input.LA(1);


                    int index50_100 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 192;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_100);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 34:
                    int LA50_194 = input.LA(1);


                    int index50_194 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 271;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_194);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 35:
                    int LA50_107 = input.LA(1);


                    int index50_107 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 199;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_107);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 36:
                    int LA50_106 = input.LA(1);


                    int index50_106 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 198;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_106);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 37:
                    int LA50_197 = input.LA(1);


                    int index50_197 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 273;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_197);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 38:
                    int LA50_193 = input.LA(1);


                    int index50_193 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 270;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_193);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 39:
                    int LA50_83 = input.LA(1);


                    int index50_83 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 175;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_83);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 40:
                    int LA50_196 = input.LA(1);


                    int index50_196 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 272;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_196);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 41:
                    int LA50_103 = input.LA(1);


                    int index50_103 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 195;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_103);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 42:
                    int LA50_202 = input.LA(1);


                    int index50_202 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 275;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_202);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 43:
                    int LA50_162 = input.LA(1);


                    int index50_162 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 253;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_162);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 44:
                    int LA50_276 = input.LA(1);


                    int index50_276 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 325;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_276);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 45:
                    int LA50_109 = input.LA(1);


                    int index50_109 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 201;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_109);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 46:
                    int LA50_200 = input.LA(1);


                    int index50_200 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 274;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_200);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 47:
                    int LA50_112 = input.LA(1);


                    int index50_112 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 204;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_112);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 48:
                    int LA50_254 = input.LA(1);


                    int index50_254 = input.index();
                    input.rewind();

                    s = -1;
                    if ((!(((!inDirective))))) {
                        s = 322;
                    } else if (((!inDirective))) {
                        s = 161;
                    }


                    input.seek(index50_254);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 49:
                    int LA50_231 = input.LA(1);


                    int index50_231 = input.index();
                    input.rewind();

                    s = -1;
                    if (((inDefineMode))) {
                        s = 61;
                    } else if (((!inDirective && !inDefineMode))) {
                        s = 62;
                    }


                    input.seek(index50_231);

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 50:
                    int LA50_346 = input.LA(1);

                    s = -1;
                    if (((LA50_346 >= '\u0000' && LA50_346 <= '\t') || (LA50_346 >= '\u000B' && LA50_346 <= '#') || (LA50_346 >= '%' && LA50_346 <= '/') || (LA50_346 >= ':' && LA50_346 <= '@') || (LA50_346 >= '[' && LA50_346 <= '^') || LA50_346 == '`' || (LA50_346 >= '{' && LA50_346 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_346 == '$' || (LA50_346 >= '0' && LA50_346 <= '9') || (LA50_346 >= 'A' && LA50_346 <= 'Z') || LA50_346 == '_' || (LA50_346 >= 'a' && LA50_346 <= 'z'))) {
                        s = 372;
                    } else {
                        s = 231;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 51:
                    int LA50_390 = input.LA(1);

                    s = -1;
                    if (((LA50_390 >= '\u0000' && LA50_390 <= '\t') || (LA50_390 >= '\u000B' && LA50_390 <= '#') || (LA50_390 >= '%' && LA50_390 <= '/') || (LA50_390 >= ':' && LA50_390 <= '@') || (LA50_390 >= '[' && LA50_390 <= '^') || LA50_390 == '`' || (LA50_390 >= '{' && LA50_390 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_390 == '$' || (LA50_390 >= '0' && LA50_390 <= '9') || (LA50_390 >= 'A' && LA50_390 <= 'Z') || LA50_390 == '_' || (LA50_390 >= 'a' && LA50_390 <= 'z'))) {
                        s = 390;
                    } else {
                        s = 231;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;

                case 52:
                    int LA50_401 = input.LA(1);

                    s = -1;
                    if (((LA50_401 >= '\u0000' && LA50_401 <= '\t') || (LA50_401 >= '\u000B' && LA50_401 <= '#') || (LA50_401 >= '%' && LA50_401 <= '/') || (LA50_401 >= ':' && LA50_401 <= '@') || (LA50_401 >= '[' && LA50_401 <= '^') || LA50_401 == '`' || (LA50_401 >= '{' && LA50_401 <= '\uFFFF'))) {
                        s = 62;
                    } else if ((LA50_401 == '$' || (LA50_401 >= '0' && LA50_401 <= '9') || (LA50_401 >= 'A' && LA50_401 <= 'Z') || LA50_401 == '_' || (LA50_401 >= 'a' && LA50_401 <= 'z'))) {
                        s = 401;
                    } else {
                        s = 231;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (backtracking > 0) {
                failed = true;
                return -1;
            }

            NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 50, _s, input);
            error(nvae);
            throw nvae;
        }
    }
}
