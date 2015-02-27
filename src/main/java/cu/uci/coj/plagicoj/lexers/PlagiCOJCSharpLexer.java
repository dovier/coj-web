package cu.uci.coj.plagicoj.lexers;

// $ANTLR 3.4 D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g 2012-03-08 15:24:18
import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PlagiCOJCSharpLexer extends Lexer {

    public static final int EOF = -1;
    public static final int ABS = 4;
    public static final int ACCESS = 5;
    public static final int ACROSS = 6;
    public static final int AFTER = 7;
    public static final int ALIAS = 8;
    public static final int ALL = 9;
    public static final int AMPERSAND = 10;
    public static final int AND = 11;
    public static final int ARCHITECTURE = 12;
    public static final int ARRAY = 13;
    public static final int ARROW = 14;
    public static final int ASSERT = 15;
    public static final int EQUALITY_OPERATOR = 100;
    public static final int ATTRIBUTE = 17;
    public static final int BACKSLASH = 18;
    public static final int BAR = 19;
    public static final int IDENTIFIER = 1000;
    public static final int BEGIN = 21;
    public static final int BLOCK = 22;
    public static final int BODY = 23;
    public static final int BOX = 24;
    public static final int BREAK = 25;
    public static final int BUFFER = 26;
    public static final int BUS = 27;
    public static final int CASE = 28;
    public static final int CHARACTER_LITERAL = 29;
    public static final int COLON = 30;
    public static final int COMMA = 31;
    public static final int COMMENT = 32;
    public static final int COMPONENT = 33;
    public static final int CONFIGURATION = 34;
    public static final int CONSTANT = 35;
    public static final int DBLQUOTE = 36;
    public static final int DECIMAL_LITERAL = 37;
    public static final int DISCONNECT = 38;
    public static final int DIV = 39;
    public static final int DOT = 40;
    public static final int DOUBLESTAR = 41;
    public static final int DOWNTO = 42;
    public static final int ELSE = 43;
    public static final int ELSIF = 44;
    public static final int END = 45;
    public static final int ENTITY = 46;
    public static final int EQ = 47;
    public static final int EXIT = 48;
    public static final int EXPONENT = 49;
    public static final int EXTENDED_IDENTIFIER = 50;
    public static final int FILE = 51;
    public static final int FOR = 300;
    public static final int FUNCTION = 53;
    public static final int GREATER_OR_EQUAL_OPERATOR = 105;
    public static final int GENERATE = 55;
    public static final int GENERIC = 56;
    public static final int GREATER_THAN_OPERATOR = 103;
    public static final int GROUP = 58;
    public static final int GUARDED = 59;
    public static final int IF = 350;
    public static final int IMPURE = 61;
    public static final int IN = 62;
    public static final int INERTIAL = 63;
    public static final int INOUT = 64;
    public static final int INTEGER = 65;
    public static final int IS = 66;
    public static final int LABEL = 67;
    public static final int LBRACKET = 68;
    public static final int LESS_OR_EQUAL_OPERATOR = 104;
    public static final int LETTER = 70;
    public static final int LIBRARY = 71;
    public static final int LIMIT = 72;
    public static final int LINKAGE = 73;
    public static final int LITERAL = 74;
    public static final int LOOP = 75;
    public static final int LOWER_THAN_OPERATOR = 102;
    public static final int LPAREN = 77;
    public static final int MAP = 78;
    public static final int MINUS = 79;
    public static final int MOD = 80;
    public static final int MUL = 81;
    public static final int NAND = 82;
    public static final int NATURE = 83;
    public static final int INEQUALITY_OPERATOR = 101;
    public static final int NEW = 85;
    public static final int NEWLINE = 86;
    public static final int NEXT = 87;
    public static final int NOISE = 88;
    public static final int NOR = 89;
    public static final int NEGATION_OPERATOR = 106;
    public static final int NULL = 91;
    public static final int OF = 92;
    public static final int ON = 93;
    public static final int OPEN = 94;
    public static final int OR = 95;
    public static final int OTHERS = 96;
    public static final int OTHER_SPECIAL_CHARACTER = 97;
    public static final int OUT = 98;
    public static final int PACKAGE = 99;
    public static final int PLUS = 100;
    public static final int PORT = 101;
    public static final int POSTPONED = 102;
    public static final int PROCEDURAL = 103;
    public static final int PROCEDURE = 104;
    public static final int PROCESS = 105;
    public static final int PURE = 106;
    public static final int QUANTITY = 107;
    public static final int RANGE = 108;
    public static final int RBRACKET = 109;
    public static final int RECORD = 110;
    public static final int REFERENCE = 111;
    public static final int REGISTER = 112;
    public static final int REJECT = 113;
    public static final int REM = 114;
    public static final int REPORT = 115;
    public static final int RETURN = 116;
    public static final int ROL = 117;
    public static final int ROR = 118;
    public static final int RPAREN = 119;
    public static final int SELECT = 120;
    public static final int SEMI = 121;
    public static final int SEVERITY = 122;
    public static final int SHARED = 123;
    public static final int SIGNAL = 124;
    public static final int SLA = 125;
    public static final int SLL = 126;
    public static final int SPACE = 127;
    public static final int SPECTRUM = 128;
    public static final int SRA = 129;
    public static final int SRL = 130;
    public static final int STRING_LITERAL = 131;
    public static final int SUBNATURE = 132;
    public static final int SUBTYPE = 133;
    public static final int TAB = 134;
    public static final int TERMINAL = 135;
    public static final int THEN = 136;
    public static final int THROUGH = 137;
    public static final int TO = 138;
    public static final int TOLERANCE = 139;
    public static final int TRANSPORT = 140;
    public static final int TYPE = 141;
    public static final int UNAFFECTED = 142;
    public static final int UNITS = 143;
    public static final int UNTIL = 144;
    public static final int USE = 145;
    public static final int VARASGN = 146;
    public static final int VARIABLE = 147;
    public static final int WAIT = 148;
    public static final int WHEN = 149;
    public static final int WHILE = 301;
    public static final int WITH = 151;
    public static final int XNOR = 152;
    public static final int XOR = 153;

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[]{};
    }

    public PlagiCOJCSharpLexer() {
    }

    public PlagiCOJCSharpLexer(CharStream input) {
        super(input);
    }

    public String getGrammarFileName() {
        return "D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g";
    }

    // $ANTLR start "ABS"
    public final void mABS() throws RecognitionException {
        try {
            int _type = ABS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:2:5: ( 'abs' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:2:7: 'abs'
            {
                match("abs");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ABS"

    // $ANTLR start "ACCESS"
    public final void mACCESS() throws RecognitionException {
        try {
            int _type = ACCESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:3:8: ( 'access' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:3:10: 'access'
            {
                match("access");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ACCESS"

    // $ANTLR start "ACROSS"
    public final void mACROSS() throws RecognitionException {
        try {
            int _type = ACROSS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:4:8: ( 'across' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:4:10: 'across'
            {
                match("across");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ACROSS"

    // $ANTLR start "AFTER"
    public final void mAFTER() throws RecognitionException {
        try {
            int _type = AFTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:5:7: ( 'after' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:5:9: 'after'
            {
                match("after");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "AFTER"

    // $ANTLR start "ALIAS"
    public final void mALIAS() throws RecognitionException {
        try {
            int _type = ALIAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:6:7: ( 'alias' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:6:9: 'alias'
            {
                match("alias");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ALIAS"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:7:5: ( 'all' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:7:7: 'all'
            {
                match("all");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:8:5: ( 'and' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:8:7: 'and'
            {
                match("and");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "ARCHITECTURE"
    public final void mARCHITECTURE() throws RecognitionException {
        try {
            int _type = ARCHITECTURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:9:14: ( 'architecture' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:9:16: 'architecture'
            {
                match("architecture");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ARCHITECTURE"

    // $ANTLR start "ARRAY"
    public final void mARRAY() throws RecognitionException {
        try {
            int _type = ARRAY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:10:7: ( 'array' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:10:9: 'array'
            {
                match("array");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ARRAY"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:11:8: ( 'assert' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:11:10: 'assert'
            {
                match("assert");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "ATTRIBUTE"
    public final void mATTRIBUTE() throws RecognitionException {
        try {
            int _type = ATTRIBUTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:12:11: ( 'attribute' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:12:13: 'attribute'
            {
                match("attribute");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ATTRIBUTE"

    // $ANTLR start "BEGIN"
    public final void mBEGIN() throws RecognitionException {
        try {
            int _type = BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:13:7: ( 'begin' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:13:9: 'begin'
            {
                match("begin");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BEGIN"

    // $ANTLR start "BLOCK"
    public final void mBLOCK() throws RecognitionException {
        try {
            int _type = BLOCK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:14:7: ( 'block' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:14:9: 'block'
            {
                match("block");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BLOCK"

    // $ANTLR start "BODY"
    public final void mBODY() throws RecognitionException {
        try {
            int _type = BODY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:15:6: ( 'body' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:15:8: 'body'
            {
                match("body");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BODY"

    // $ANTLR start "BREAK"
    public final void mBREAK() throws RecognitionException {
        try {
            int _type = BREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:16:7: ( 'break' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:16:9: 'break'
            {
                match("break");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BREAK"

    // $ANTLR start "BUFFER"
    public final void mBUFFER() throws RecognitionException {
        try {
            int _type = BUFFER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:17:8: ( 'buffer' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:17:10: 'buffer'
            {
                match("buffer");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BUFFER"

    // $ANTLR start "BUS"
    public final void mBUS() throws RecognitionException {
        try {
            int _type = BUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:18:5: ( 'bus' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:18:7: 'bus'
            {
                match("bus");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BUS"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:19:6: ( 'case' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:19:8: 'case'
            {
                match("case");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "COMPONENT"
    public final void mCOMPONENT() throws RecognitionException {
        try {
            int _type = COMPONENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:20:11: ( 'component' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:20:13: 'component'
            {
                match("component");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "COMPONENT"

    // $ANTLR start "CONFIGURATION"
    public final void mCONFIGURATION() throws RecognitionException {
        try {
            int _type = CONFIGURATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:21:15: ( 'configuration' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:21:17: 'configuration'
            {
                match("configuration");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "CONFIGURATION"

    // $ANTLR start "CONSTANT"
    public final void mCONSTANT() throws RecognitionException {
        try {
            int _type = CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:22:10: ( 'constant' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:22:12: 'constant'
            {
                match("constant");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "CONSTANT"

    // $ANTLR start "DISCONNECT"
    public final void mDISCONNECT() throws RecognitionException {
        try {
            int _type = DISCONNECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:23:12: ( 'disconnect' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:23:14: 'disconnect'
            {
                match("disconnect");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DISCONNECT"

    // $ANTLR start "DOWNTO"
    public final void mDOWNTO() throws RecognitionException {
        try {
            int _type = DOWNTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:24:8: ( 'downto' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:24:10: 'downto'
            {
                match("downto");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DOWNTO"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:25:6: ( 'else' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:25:8: 'else'
            {
                match("else");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "ELSIF"
    public final void mELSIF() throws RecognitionException {
        try {
            int _type = ELSIF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:26:7: ( 'elsif' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:26:9: 'elsif'
            {
                match("elsif");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ELSIF"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:27:5: ( 'end' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:27:7: 'end'
            {
                match("end");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "ENTITY"
    public final void mENTITY() throws RecognitionException {
        try {
            int _type = ENTITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:28:8: ( 'entity' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:28:10: 'entity'
            {
                match("entity");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ENTITY"

    // $ANTLR start "EXIT"
    public final void mEXIT() throws RecognitionException {
        try {
            int _type = EXIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:29:6: ( 'exit' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:29:8: 'exit'
            {
                match("exit");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EXIT"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            int _type = FILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:30:6: ( 'file' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:30:8: 'file'
            {
                match("file");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:31:5: ( 'for' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:31:7: 'for'
            {
                match("for");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "FUNCTION"
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:32:10: ( 'function' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:32:12: 'function'
            {
                match("function");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "FUNCTION"

    // $ANTLR start "GENERATE"
    public final void mGENERATE() throws RecognitionException {
        try {
            int _type = GENERATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:33:10: ( 'generate' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:33:12: 'generate'
            {
                match("generate");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GENERATE"

    // $ANTLR start "GENERIC"
    public final void mGENERIC() throws RecognitionException {
        try {
            int _type = GENERIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:34:9: ( 'generic' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:34:11: 'generic'
            {
                match("generic");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GENERIC"

    // $ANTLR start "GROUP"
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:35:7: ( 'group' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:35:9: 'group'
            {
                match("group");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GROUP"

    // $ANTLR start "GUARDED"
    public final void mGUARDED() throws RecognitionException {
        try {
            int _type = GUARDED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:36:9: ( 'guarded' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:36:11: 'guarded'
            {
                match("guarded");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GUARDED"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:37:4: ( 'if' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:37:6: 'if'
            {
                match("if");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "IMPURE"
    public final void mIMPURE() throws RecognitionException {
        try {
            int _type = IMPURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:38:8: ( 'impure' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:38:10: 'impure'
            {
                match("impure");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "IMPURE"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:39:4: ( 'in' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:39:6: 'in'
            {
                match("in");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "INERTIAL"
    public final void mINERTIAL() throws RecognitionException {
        try {
            int _type = INERTIAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:40:10: ( 'inertial' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:40:12: 'inertial'
            {
                match("inertial");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "INERTIAL"

    // $ANTLR start "INOUT"
    public final void mINOUT() throws RecognitionException {
        try {
            int _type = INOUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:41:7: ( 'inout' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:41:9: 'inout'
            {
                match("inout");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "INOUT"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:42:4: ( 'is' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:42:6: 'is'
            {
                match("is");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "LABEL"
    public final void mLABEL() throws RecognitionException {
        try {
            int _type = LABEL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:43:7: ( 'label' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:43:9: 'label'
            {
                match("label");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LABEL"

    // $ANTLR start "LIBRARY"
    public final void mLIBRARY() throws RecognitionException {
        try {
            int _type = LIBRARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:44:9: ( 'library' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:44:11: 'library'
            {
                match("library");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LIBRARY"

    // $ANTLR start "LIMIT"
    public final void mLIMIT() throws RecognitionException {
        try {
            int _type = LIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:45:7: ( 'limit' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:45:9: 'limit'
            {
                match("limit");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LIMIT"

    // $ANTLR start "LINKAGE"
    public final void mLINKAGE() throws RecognitionException {
        try {
            int _type = LINKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:46:9: ( 'linkage' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:46:11: 'linkage'
            {
                match("linkage");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LINKAGE"

    // $ANTLR start "LITERAL"
    public final void mLITERAL() throws RecognitionException {
        try {
            int _type = LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:47:9: ( 'literal' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:47:11: 'literal'
            {
                match("literal");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LITERAL"

    // $ANTLR start "LOOP"
    public final void mLOOP() throws RecognitionException {
        try {
            int _type = LOOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:48:6: ( 'loop' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:48:8: 'loop'
            {
                match("loop");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LOOP"

    // $ANTLR start "MAP"
    public final void mMAP() throws RecognitionException {
        try {
            int _type = MAP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:49:5: ( 'map' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:49:7: 'map'
            {
                match("map");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "MAP"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:50:5: ( 'mod' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:50:7: 'mod'
            {
                match("mod");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "NAND"
    public final void mNAND() throws RecognitionException {
        try {
            int _type = NAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:51:6: ( 'nand' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:51:8: 'nand'
            {
                match("nand");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NAND"

    // $ANTLR start "NATURE"
    public final void mNATURE() throws RecognitionException {
        try {
            int _type = NATURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:52:8: ( 'nature' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:52:10: 'nature'
            {
                match("nature");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NATURE"

    // $ANTLR start "NEW"
    public final void mNEW() throws RecognitionException {
        try {
            int _type = NEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:53:5: ( 'new' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:53:7: 'new'
            {
                match("new");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NEW"

    // $ANTLR start "NEXT"
    public final void mNEXT() throws RecognitionException {
        try {
            int _type = NEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:54:6: ( 'next' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:54:8: 'next'
            {
                match("next");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NEXT"

    // $ANTLR start "NOISE"
    public final void mNOISE() throws RecognitionException {
        try {
            int _type = NOISE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:55:7: ( 'noise' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:55:9: 'noise'
            {
                match("noise");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NOISE"

    // $ANTLR start "NOR"
    public final void mNOR() throws RecognitionException {
        try {
            int _type = NOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:56:5: ( 'nor' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:56:7: 'nor'
            {
                match("nor");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NOR"

    // $ANTLR start "NOT"
    public final void mNEGATION_OPERATOR() throws RecognitionException {
        try {
            int _type = NEGATION_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:57:5: ( 'not' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:57:7: 'not'
            {
                match("not");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:58:6: ( 'null' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:58:8: 'null'
            {
                match("null");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "OF"
    public final void mOF() throws RecognitionException {
        try {
            int _type = OF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:59:4: ( 'of' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:59:6: 'of'
            {
                match("of");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OF"

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:60:4: ( 'on' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:60:6: 'on'
            {
                match("on");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ON"

    // $ANTLR start "OPEN"
    public final void mOPEN() throws RecognitionException {
        try {
            int _type = OPEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:61:6: ( 'open' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:61:8: 'open'
            {
                match("open");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OPEN"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:62:4: ( 'or' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:62:6: 'or'
            {
                match("or");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "OTHERS"
    public final void mOTHERS() throws RecognitionException {
        try {
            int _type = OTHERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:63:8: ( 'others' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:63:10: 'others'
            {
                match("others");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OTHERS"

    // $ANTLR start "OUT"
    public final void mOUT() throws RecognitionException {
        try {
            int _type = OUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:64:5: ( 'out' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:64:7: 'out'
            {
                match("out");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OUT"

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:65:9: ( 'package' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:65:11: 'package'
            {
                match("package");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "PORT"
    public final void mPORT() throws RecognitionException {
        try {
            int _type = PORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:66:6: ( 'port' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:66:8: 'port'
            {
                match("port");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PORT"

    // $ANTLR start "POSTPONED"
    public final void mPOSTPONED() throws RecognitionException {
        try {
            int _type = POSTPONED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:67:11: ( 'postponed' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:67:13: 'postponed'
            {
                match("postponed");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "POSTPONED"

    // $ANTLR start "PROCEDURAL"
    public final void mPROCEDURAL() throws RecognitionException {
        try {
            int _type = PROCEDURAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:68:12: ( 'procedural' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:68:14: 'procedural'
            {
                match("procedural");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PROCEDURAL"

    // $ANTLR start "PROCEDURE"
    public final void mPROCEDURE() throws RecognitionException {
        try {
            int _type = PROCEDURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:69:11: ( 'procedure' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:69:13: 'procedure'
            {
                match("procedure");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PROCEDURE"

    // $ANTLR start "PROCESS"
    public final void mPROCESS() throws RecognitionException {
        try {
            int _type = PROCESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:70:9: ( 'process' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:70:11: 'process'
            {
                match("process");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PROCESS"

    // $ANTLR start "PURE"
    public final void mPURE() throws RecognitionException {
        try {
            int _type = PURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:71:6: ( 'pure' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:71:8: 'pure'
            {
                match("pure");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PURE"

    // $ANTLR start "QUANTITY"
    public final void mQUANTITY() throws RecognitionException {
        try {
            int _type = QUANTITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:72:10: ( 'quantity' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:72:12: 'quantity'
            {
                match("quantity");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "QUANTITY"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:73:7: ( 'range' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:73:9: 'range'
            {
                match("range");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "RECORD"
    public final void mRECORD() throws RecognitionException {
        try {
            int _type = RECORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:74:8: ( 'record' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:74:10: 'record'
            {
                match("record");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RECORD"

    // $ANTLR start "REFERENCE"
    public final void mREFERENCE() throws RecognitionException {
        try {
            int _type = REFERENCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:75:11: ( 'reference' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:75:13: 'reference'
            {
                match("reference");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REFERENCE"

    // $ANTLR start "REGISTER"
    public final void mREGISTER() throws RecognitionException {
        try {
            int _type = REGISTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:76:10: ( 'register' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:76:12: 'register'
            {
                match("register");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REGISTER"

    // $ANTLR start "REJECT"
    public final void mREJECT() throws RecognitionException {
        try {
            int _type = REJECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:77:8: ( 'reject' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:77:10: 'reject'
            {
                match("reject");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REJECT"

    // $ANTLR start "REM"
    public final void mREM() throws RecognitionException {
        try {
            int _type = REM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:78:5: ( 'rem' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:78:7: 'rem'
            {
                match("rem");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REM"

    // $ANTLR start "REPORT"
    public final void mREPORT() throws RecognitionException {
        try {
            int _type = REPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:79:8: ( 'report' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:79:10: 'report'
            {
                match("report");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "REPORT"

    // $ANTLR start "RETURN"
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:80:8: ( 'return' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:80:10: 'return'
            {
                match("return");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RETURN"

    // $ANTLR start "ROL"
    public final void mROL() throws RecognitionException {
        try {
            int _type = ROL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:81:5: ( 'rol' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:81:7: 'rol'
            {
                match("rol");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ROL"

    // $ANTLR start "ROR"
    public final void mROR() throws RecognitionException {
        try {
            int _type = ROR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:82:5: ( 'ror' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:82:7: 'ror'
            {
                match("ror");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ROR"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:83:8: ( 'select' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:83:10: 'select'
            {
                match("select");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SELECT"

    // $ANTLR start "SEVERITY"
    public final void mSEVERITY() throws RecognitionException {
        try {
            int _type = SEVERITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:84:10: ( 'severity' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:84:12: 'severity'
            {
                match("severity");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SEVERITY"

    // $ANTLR start "SHARED"
    public final void mSHARED() throws RecognitionException {
        try {
            int _type = SHARED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:85:8: ( 'shared' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:85:10: 'shared'
            {
                match("shared");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SHARED"

    // $ANTLR start "SIGNAL"
    public final void mSIGNAL() throws RecognitionException {
        try {
            int _type = SIGNAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:86:8: ( 'signal' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:86:10: 'signal'
            {
                match("signal");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SIGNAL"

    // $ANTLR start "SLA"
    public final void mSLA() throws RecognitionException {
        try {
            int _type = SLA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:87:5: ( 'sla' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:87:7: 'sla'
            {
                match("sla");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SLA"

    // $ANTLR start "SLL"
    public final void mSLL() throws RecognitionException {
        try {
            int _type = SLL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:88:5: ( 'sll' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:88:7: 'sll'
            {
                match("sll");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SLL"

    // $ANTLR start "SPECTRUM"
    public final void mSPECTRUM() throws RecognitionException {
        try {
            int _type = SPECTRUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:89:10: ( 'spectrum' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:89:12: 'spectrum'
            {
                match("spectrum");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SPECTRUM"

    // $ANTLR start "SRA"
    public final void mSRA() throws RecognitionException {
        try {
            int _type = SRA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:90:5: ( 'sra' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:90:7: 'sra'
            {
                match("sra");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SRA"

    // $ANTLR start "SRL"
    public final void mSRL() throws RecognitionException {
        try {
            int _type = SRL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:91:5: ( 'srl' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:91:7: 'srl'
            {
                match("srl");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SRL"

    // $ANTLR start "SUBNATURE"
    public final void mSUBNATURE() throws RecognitionException {
        try {
            int _type = SUBNATURE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:92:11: ( 'subnature' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:92:13: 'subnature'
            {
                match("subnature");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SUBNATURE"

    // $ANTLR start "SUBTYPE"
    public final void mSUBTYPE() throws RecognitionException {
        try {
            int _type = SUBTYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:93:9: ( 'subtype' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:93:11: 'subtype'
            {
                match("subtype");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SUBTYPE"

    // $ANTLR start "TERMINAL"
    public final void mTERMINAL() throws RecognitionException {
        try {
            int _type = TERMINAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:94:10: ( 'terminal' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:94:12: 'terminal'
            {
                match("terminal");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TERMINAL"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:95:6: ( 'then' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:95:8: 'then'
            {
                match("then");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "THROUGH"
    public final void mTHROUGH() throws RecognitionException {
        try {
            int _type = THROUGH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:96:9: ( 'through' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:96:11: 'through'
            {
                match("through");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "THROUGH"

    // $ANTLR start "TO"
    public final void mTO() throws RecognitionException {
        try {
            int _type = TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:97:4: ( 'to' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:97:6: 'to'
            {
                match("to");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TO"

    // $ANTLR start "TOLERANCE"
    public final void mTOLERANCE() throws RecognitionException {
        try {
            int _type = TOLERANCE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:98:11: ( 'tolerance' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:98:13: 'tolerance'
            {
                match("tolerance");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TOLERANCE"

    // $ANTLR start "TRANSPORT"
    public final void mTRANSPORT() throws RecognitionException {
        try {
            int _type = TRANSPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:99:11: ( 'transport' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:99:13: 'transport'
            {
                match("transport");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TRANSPORT"

    // $ANTLR start "TYPE"
    public final void mTYPE() throws RecognitionException {
        try {
            int _type = TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:100:6: ( 'type' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:100:8: 'type'
            {
                match("type");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TYPE"

    // $ANTLR start "UNAFFECTED"
    public final void mUNAFFECTED() throws RecognitionException {
        try {
            int _type = UNAFFECTED;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:101:12: ( 'unaffected' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:101:14: 'unaffected'
            {
                match("unaffected");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "UNAFFECTED"

    // $ANTLR start "UNITS"
    public final void mUNITS() throws RecognitionException {
        try {
            int _type = UNITS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:102:7: ( 'units' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:102:9: 'units'
            {
                match("units");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "UNITS"

    // $ANTLR start "UNTIL"
    public final void mUNTIL() throws RecognitionException {
        try {
            int _type = UNTIL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:103:7: ( 'until' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:103:9: 'until'
            {
                match("until");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "UNTIL"

    // $ANTLR start "USE"
    public final void mUSE() throws RecognitionException {
        try {
            int _type = USE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:104:5: ( 'use' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:104:7: 'use'
            {
                match("use");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "USE"

    // $ANTLR start "VARIABLE"
    public final void mVARIABLE() throws RecognitionException {
        try {
            int _type = VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:105:10: ( 'variable' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:105:12: 'variable'
            {
                match("variable");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "VARIABLE"

    // $ANTLR start "WAIT"
    public final void mWAIT() throws RecognitionException {
        try {
            int _type = WAIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:106:6: ( 'wait' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:106:8: 'wait'
            {
                match("wait");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WAIT"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:107:6: ( 'when' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:107:8: 'when'
            {
                match("when");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WHEN"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:108:7: ( 'while' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:108:9: 'while'
            {
                match("while");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "WITH"
    public final void mWITH() throws RecognitionException {
        try {
            int _type = WITH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:109:6: ( 'with' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:109:8: 'with'
            {
                match("with");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WITH"

    // $ANTLR start "XNOR"
    public final void mXNOR() throws RecognitionException {
        try {
            int _type = XNOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:110:6: ( 'xnor' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:110:8: 'xnor'
            {
                match("xnor");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "XNOR"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:111:5: ( 'xor' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:111:7: 'xor'
            {
                match("xor");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1507:3: ( '0' .. '9' ( '_' | '0' .. '9' )* )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1507:6: '0' .. '9' ( '_' | '0' .. '9' )*
            {
                matchRange('0', '9');

                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1507:15: ( '_' | '0' .. '9' )*
                loop1:
                do {
                    int alt1 = 2;
                    int LA1_0 = input.LA(1);

                    if (((LA1_0 >= '0' && LA1_0 <= '9') || LA1_0 == '_')) {
                        alt1 = 1;
                    }


                    switch (alt1) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || input.LA(1) == '_') {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop1;
                    }
                } while (true);


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            int _type = LETTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1511:3: ( 'a' .. 'z' | 'A' .. 'Z' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
            {
                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            int _type = DECIMAL_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:4: ( INTEGER ( ( '.' INTEGER )? ( EXPONENT )? ) )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:6: INTEGER ( ( '.' INTEGER )? ( EXPONENT )? )
            {
                mINTEGER();


                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:14: ( ( '.' INTEGER )? ( EXPONENT )? )
                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:16: ( '.' INTEGER )? ( EXPONENT )?
                {
                    // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:16: ( '.' INTEGER )?
                    int alt2 = 2;
                    int LA2_0 = input.LA(1);

                    if ((LA2_0 == '.')) {
                        alt2 = 1;
                    }
                    switch (alt2) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:18: '.' INTEGER
                        {
                            match('.');

                            mINTEGER();


                        }
                        break;

                    }


                    // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:33: ( EXPONENT )?
                    int alt3 = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == 'e')) {
                        alt3 = 1;
                    }
                    switch (alt3) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1515:35: EXPONENT
                        {
                            mEXPONENT();


                        }
                        break;

                    }


                }


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "BASIC_IDENTIFIER"
    public final void mBASIC_IDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1520:4: ( LETTER ( '_' | LETTER | '0' .. '9' )* )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1520:8: LETTER ( '_' | LETTER | '0' .. '9' )*
            {
                mLETTER();


                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1520:15: ( '_' | LETTER | '0' .. '9' )*
                loop4:
                do {
                    int alt4 = 2;
                    int LA4_0 = input.LA(1);

                    if (((LA4_0 >= '0' && LA4_0 <= '9') || (LA4_0 >= 'A' && LA4_0 <= 'Z') || LA4_0 == '_' || (LA4_0 >= 'a' && LA4_0 <= 'z'))) {
                        alt4 = 1;
                    }


                    switch (alt4) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
                        {
                            if ((input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop4;
                    }
                } while (true);


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BASIC_IDENTIFIER"

    // $ANTLR start "EXTENDED_IDENTIFIER"
    public final void mEXTENDED_IDENTIFIER() throws RecognitionException {
        try {
            int _type = EXTENDED_IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1524:3: ( '\\\\' ( '&' | '\\'' | '(' | ')' | '+' | ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '|' | ' ' | OTHER_SPECIAL_CHARACTER | '\\\\' | '#' | '[' | ']' | '_' )+ '\\\\' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1524:5: '\\\\' ( '&' | '\\'' | '(' | ')' | '+' | ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '|' | ' ' | OTHER_SPECIAL_CHARACTER | '\\\\' | '#' | '[' | ']' | '_' )+ '\\\\'
            {
                match('\\');

                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1524:10: ( '&' | '\\'' | '(' | ')' | '+' | ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '|' | ' ' | OTHER_SPECIAL_CHARACTER | '\\\\' | '#' | '[' | ']' | '_' )+
                int cnt5 = 0;
                loop5:
                do {
                    int alt5 = 2;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == '\\')) {
                        int LA5_1 = input.LA(2);

                        if (((LA5_1 >= ' ' && LA5_1 <= '!') || (LA5_1 >= '#' && LA5_1 <= ')') || (LA5_1 >= '+' && LA5_1 <= '/') || (LA5_1 >= ':' && LA5_1 <= '@') || (LA5_1 >= '[' && LA5_1 <= '`') || (LA5_1 >= '{' && LA5_1 <= '~') || (LA5_1 >= '\u00A1' && LA5_1 <= '\u00FF'))) {
                            alt5 = 1;
                        }


                    } else if (((LA5_0 >= ' ' && LA5_0 <= '!') || (LA5_0 >= '#' && LA5_0 <= ')') || (LA5_0 >= '+' && LA5_0 <= '/') || (LA5_0 >= ':' && LA5_0 <= '@') || LA5_0 == '[' || (LA5_0 >= ']' && LA5_0 <= '`') || (LA5_0 >= '{' && LA5_0 <= '~') || (LA5_0 >= '\u00A1' && LA5_0 <= '\u00FF'))) {
                        alt5 = 1;
                    }


                    switch (alt5) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
                        {
                            if ((input.LA(1) >= ' ' && input.LA(1) <= '!') || (input.LA(1) >= '#' && input.LA(1) <= ')') || (input.LA(1) >= '+' && input.LA(1) <= '/') || (input.LA(1) >= ':' && input.LA(1) <= '@') || (input.LA(1) >= '[' && input.LA(1) <= '`') || (input.LA(1) >= '{' && input.LA(1) <= '~') || (input.LA(1) >= '\u00A1' && input.LA(1) <= '\u00FF')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            if (cnt5 >= 1) {
                                break loop5;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(5, input);
                            throw eee;
                    }
                    cnt5++;
                } while (true);


                match('\\');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EXTENDED_IDENTIFIER"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1532:3: ( '--' (~ '\\n' )* )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1532:5: '--' (~ '\\n' )*
            {
                match("--");



                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1532:10: (~ '\\n' )*
                loop6:
                do {
                    int alt6 = 2;
                    int LA6_0 = input.LA(1);

                    if (((LA6_0 >= '\u0000' && LA6_0 <= '\t') || (LA6_0 >= '\u000B' && LA6_0 <= '\uFFFF'))) {
                        alt6 = 1;
                    }


                    switch (alt6) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
                        {
                            if ((input.LA(1) >= '\u0000' && input.LA(1) <= '\t') || (input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF')) {
                                input.consume();
                            } else {
                                MismatchedSetException mse = new MismatchedSetException(null, input);
                                recover(mse);
                                throw mse;
                            }


                        }
                        break;

                        default:
                            break loop6;
                    }
                } while (true);

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "TAB"
    public final void mTAB() throws RecognitionException {
        try {
            int _type = TAB;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1536:3: ( ( '\\t' )+ )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1536:5: ( '\\t' )+
            {
                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1536:5: ( '\\t' )+
                int cnt7 = 0;
                loop7:
                do {
                    int alt7 = 2;
                    int LA7_0 = input.LA(1);

                    if ((LA7_0 == '\t')) {
                        alt7 = 1;
                    }


                    switch (alt7) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1536:7: '\\t'
                        {
                            match('\t');

                        }
                        break;

                        default:
                            if (cnt7 >= 1) {
                                break loop7;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(7, input);
                            throw eee;
                    }
                    cnt7++;
                } while (true);

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "TAB"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1540:3: ( ( ' ' )+ )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1540:5: ( ' ' )+
            {
                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1540:5: ( ' ' )+
                int cnt8 = 0;
                loop8:
                do {
                    int alt8 = 2;
                    int LA8_0 = input.LA(1);

                    if ((LA8_0 == ' ')) {
                        alt8 = 1;
                    }


                    switch (alt8) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1540:7: ' '
                        {
                            match(' ');

                        }
                        break;

                        default:
                            if (cnt8 >= 1) {
                                break loop8;
                            }
                            EarlyExitException eee =
                                    new EarlyExitException(8, input);
                            throw eee;
                    }
                    cnt8++;
                } while (true);

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1544:3: ( '\\n' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1544:5: '\\n'
            {
                match('\n');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "CHARACTER_LITERAL"
    public final void mCHARACTER_LITERAL() throws RecognitionException {
        try {
            int _type = CHARACTER_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1549:4: ( '\\'' ( . )* '\\'' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1549:6: '\\'' ( . )* '\\''
            {
                match('\'');

                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1549:11: ( . )*
                loop9:
                do {
                    int alt9 = 2;
                    int LA9_0 = input.LA(1);

                    if ((LA9_0 == '\'')) {
                        alt9 = 2;
                    } else if (((LA9_0 >= '\u0000' && LA9_0 <= '&') || (LA9_0 >= '(' && LA9_0 <= '\uFFFF'))) {
                        alt9 = 1;
                    }


                    switch (alt9) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1549:11: .
                        {
                            matchAny();

                        }
                        break;

                        default:
                            break loop9;
                    }
                } while (true);


                match('\'');
                

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "CHARACTER_LITERAL"

    // $ANTLR start "STRING_LITERAL"
    public final void mSTRING_LITERAL() throws RecognitionException {
        try {
            int _type = STRING_LITERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1554:3: ( '\\\"' ( . )* '\\\"' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1554:5: '\\\"' ( . )* '\\\"'
            {
                match('\"');

                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1554:10: ( . )*
                loop10:
                do {
                    int alt10 = 2;
                    int LA10_0 = input.LA(1);

                    if ((LA10_0 == '\"')) {
                        alt10 = 2;
                    } else if (((LA10_0 >= '\u0000' && LA10_0 <= '!') || (LA10_0 >= '#' && LA10_0 <= '\uFFFF'))) {
                        alt10 = 1;
                    }


                    switch (alt10) {
                        case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1554:10: .
                        {
                            matchAny();

                        }
                        break;

                        default:
                            break loop10;
                    }
                } while (true);


                match('\"');

                

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "STRING_LITERAL"

    // $ANTLR start "OTHER_SPECIAL_CHARACTER"
    public final void mOTHER_SPECIAL_CHARACTER() throws RecognitionException {
        try {
            int _type = OTHER_SPECIAL_CHARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1559:3: ( '!' | '$' | '%' | '@' | '?' | '^' | '`' | '{' | '}' | '~' | ' ' | 'Â¡' | 'Â¢' | 'Â£' | 'Â¤' | 'Â¥' | 'Â¦' | 'Â§' | 'Â¨' | 'Â©' | 'Âª' | 'Â«' | 'Â¬' | 'Â­' | 'Â®' | 'Â¯' | 'Â°' | 'Â±' | 'Â²' | 'Â³' | 'Â´' | 'Âµ' | 'Â¶' | 'Â·' | 'Â¸' | 'Â¹' | 'Âº' | 'Â»' | 'Â¼' | 'Â½' | 'Â¾' | 'Â¿' | 'Ã€' | 'Ã�' | 'Ã‚' | 'Ãƒ' | 'Ã„' | 'Ã…' | 'Ã†' | 'Ã‡' | 'Ãˆ' | 'Ã‰' | 'ÃŠ' | 'Ã‹' | 'ÃŒ' | 'Ã�' | 'ÃŽ' | 'Ã�' | 'Ã�' | 'Ã‘' | 'Ã’' | 'Ã“' | 'Ã”' | 'Ã•' | 'Ã–' | 'Ã—' | 'Ã˜' | 'Ã™' | 'Ãš' | 'Ã›' | 'Ãœ' | 'Ã�' | 'Ãž' | 'ÃŸ' | 'Ã ' | 'Ã¡' | 'Ã¢' | 'Ã£' | 'Ã¤' | 'Ã¥' | 'Ã¦' | 'Ã§' | 'Ã¨' | 'Ã©' | 'Ãª' | 'Ã«' | 'Ã¬' | 'Ã­' | 'Ã®' | 'Ã¯' | 'Ã°' | 'Ã±' | 'Ã²' | 'Ã³' | 'Ã´' | 'Ãµ' | 'Ã¶' | 'Ã·' | 'Ã¸' | 'Ã¹' | 'Ãº' | 'Ã»' | 'Ã¼' | 'Ã½' | 'Ã¾' | 'Ã¿' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
            {
                if ((input.LA(1) >= ' ' && input.LA(1) <= '!') || (input.LA(1) >= '$' && input.LA(1) <= '%') || (input.LA(1) >= '?' && input.LA(1) <= '@') || input.LA(1) == '^' || input.LA(1) == '`' || input.LA(1) == '{' || (input.LA(1) >= '}' && input.LA(1) <= '~') || (input.LA(1) >= '\u00A1' && input.LA(1) <= '\u00FF')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "OTHER_SPECIAL_CHARACTER"

    // $ANTLR start "DOUBLESTAR"
    public final void mDOUBLESTAR() throws RecognitionException {
        try {
            int _type = DOUBLESTAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1575:15: ( '**' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1575:17: '**'
            {
                match("**");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DOUBLESTAR"

    // $ANTLR start "ASSIGN"
    public final void mEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = EQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1576:15: ( '==' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1576:17: '=='
            {
                match("==");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "LE"
    public final void mLESS_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = LESS_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1577:15: ( '<=' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1577:17: '<='
            {
                match("<=");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GE"
    public final void mGREATER_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1578:15: ( '>=' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1578:17: '>='
            {
                match(">=");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "ARROW"
    public final void mARROW() throws RecognitionException {
        try {
            int _type = ARROW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1579:15: ( '=>' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1579:17: '=>'
            {
                match("=>");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ARROW"

    // $ANTLR start "NEQ"
    public final void mINEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = INEQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1580:15: ( '/=' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1580:17: '/='
            {
                match("!=");
            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NEQ"

    // $ANTLR start "VARASGN"
    public final void mVARASGN() throws RecognitionException {
        try {
            int _type = VARASGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1581:15: ( ':=' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1581:17: ':='
            {
                match(":=");



            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "VARASGN"

    // $ANTLR start "BOX"
    public final void mBOX() throws RecognitionException {
        try {
            int _type = BOX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1582:15: ( '<>' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1582:17: '<>'
            {
                match("<>");
            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BOX"

    // $ANTLR start "DBLQUOTE"
    public final void mDBLQUOTE() throws RecognitionException {
        try {
            int _type = DBLQUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1583:15: ( '\\\"' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1583:17: '\\\"'
            {
                match('\"');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DBLQUOTE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1584:15: ( ';' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1584:17: ';'
            {
                match(';');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1585:15: ( ',' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1585:17: ','
            {
                match(',');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "AMPERSAND"
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1586:15: ( '&' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1586:17: '&'
            {
                match('&');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "AMPERSAND"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1587:15: ( '(' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1587:17: '('
            {
                match('(');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1588:15: ( ')' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1588:17: ')'
            {
                match(')');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1589:15: ( '[' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1589:17: '['
            {
                match('[');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1590:15: ( ']' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1590:17: ']'
            {
                match(']');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1591:15: ( ':' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1591:17: ':'
            {
                match(':');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "MUL"
    public final void mMUL() throws RecognitionException {
        try {
            int _type = MUL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1592:15: ( '*' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1592:17: '*'
            {
                match('*');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "MUL"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1593:15: ( '/' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1593:17: '/'
            {
                match('/');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1594:15: ( '+' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1594:17: '+'
            {
                match('+');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1595:15: ( '-' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1595:17: '-'
            {
                match('-');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "LOWERTHAN"
    public final void mLOWER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = LOWER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1596:15: ( '<' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1596:17: '<'
            {
                match('<');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LOWERTHAN"

    // $ANTLR start "GREATERTHAN"
    public final void mGREATER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1597:15: ( '>' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1597:17: '>'
            {
                match('>');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "GREATERTHAN"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1598:15: ( '=' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1598:17: '='
            {
                match('=');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "BAR"
    public final void mBAR() throws RecognitionException {
        try {
            int _type = BAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1599:15: ( '|' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1599:17: '|'
            {
                match('|');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BAR"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1600:15: ( '.' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1600:17: '.'
            {
                match('.');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "BACKSLASH"
    public final void mBACKSLASH() throws RecognitionException {
        try {
            int _type = BACKSLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1601:15: ( '\\\\' )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1601:17: '\\\\'
            {
                match('\\');

            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "BACKSLASH"

    // $ANTLR start "EXPONENT"
    public final void mEXPONENT() throws RecognitionException {
        try {
            int _type = EXPONENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1605:3: ( 'e' ( '+' | '-' )? INTEGER )
            // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1605:6: 'e' ( '+' | '-' )? INTEGER
            {
                match('e');

                // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1605:10: ( '+' | '-' )?
                int alt11 = 2;
                int LA11_0 = input.LA(1);

                if ((LA11_0 == '+' || LA11_0 == '-')) {
                    alt11 = 1;
                }
                switch (alt11) {
                    case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:
                    {
                        if (input.LA(1) == '+' || input.LA(1) == '-') {
                            input.consume();
                        } else {
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }


                    }
                    break;

                }


                mINTEGER();


            }

            type = _type;
            channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT"

    public void mTokens() throws RecognitionException {
        // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:8: ( ABS | ACCESS | ACROSS | AFTER | ALIAS | ALL | AND | ARCHITECTURE | ARRAY | ASSERT | ATTRIBUTE | BEGIN | BLOCK | BODY | BREAK | BUFFER | BUS | CASE | COMPONENT | CONFIGURATION | CONSTANT | DISCONNECT | DOWNTO | ELSE | ELSIF | END | ENTITY | EXIT | FILE | FOR | FUNCTION | GENERATE | GENERIC | GROUP | GUARDED | IF | IMPURE | IN | INERTIAL | INOUT | IS | LABEL | LIBRARY | LIMIT | LINKAGE | LITERAL | LOOP | MAP | MOD | NAND | NATURE | NEW | NEXT | NOISE | NOR | NOT | NULL | OF | ON | OPEN | OR | OTHERS | OUT | PACKAGE | PORT | POSTPONED | PROCEDURAL | PROCEDURE | PROCESS | PURE | QUANTITY | RANGE | RECORD | REFERENCE | REGISTER | REJECT | REM | REPORT | RETURN | ROL | ROR | SELECT | SEVERITY | SHARED | SIGNAL | SLA | SLL | SPECTRUM | SRA | SRL | SUBNATURE | SUBTYPE | TERMINAL | THEN | THROUGH | TO | TOLERANCE | TRANSPORT | TYPE | UNAFFECTED | UNITS | UNTIL | USE | VARIABLE | WAIT | WHEN | WHILE | WITH | XNOR | XOR | INTEGER | LETTER | DECIMAL_LITERAL | BASIC_IDENTIFIER | EXTENDED_IDENTIFIER | COMMENT | TAB | SPACE | NEWLINE | CHARACTER_LITERAL | STRING_LITERAL | OTHER_SPECIAL_CHARACTER | DOUBLESTAR | ASSIGN | LE | GE | ARROW | NEQ | VARASGN | BOX | DBLQUOTE | SEMI | COMMA | AMPERSAND | LPAREN | RPAREN | LBRACKET | RBRACKET | COLON | MUL | DIV | PLUS | MINUS | LOWERTHAN | GREATERTHAN | EQ | BAR | DOT | BACKSLASH | EXPONENT )
        int alt12 = 150;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:10: ABS
            {
                mABS();


            }
            break;
            case 2: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:14: ACCESS
            {
                mACCESS();


            }
            break;
            case 3: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:21: ACROSS
            {
                mACROSS();


            }
            break;
            case 4: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:28: AFTER
            {
                mAFTER();


            }
            break;
            case 5: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:34: ALIAS
            {
                mALIAS();


            }
            break;
            case 6: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:40: ALL
            {
                mALL();


            }
            break;
            case 7: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:44: AND
            {
                mAND();


            }
            break;
            case 8: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:48: ARCHITECTURE
            {
                mARCHITECTURE();


            }
            break;
            case 9: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:61: ARRAY
            {
                mARRAY();


            }
            break;
            case 10: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:67: ASSERT
            {
                mASSERT();


            }
            break;
            case 11: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:74: ATTRIBUTE
            {
                mATTRIBUTE();


            }
            break;
            case 12: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:84: BEGIN
            {
                mBEGIN();


            }
            break;
            case 13: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:90: BLOCK
            {
                mBLOCK();


            }
            break;
            case 14: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:96: BODY
            {
                mBODY();


            }
            break;
            case 15: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:101: BREAK
            {
                mBREAK();


            }
            break;
            case 16: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:107: BUFFER
            {
                mBUFFER();


            }
            break;
            case 17: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:114: BUS
            {
                mBUS();


            }
            break;
            case 18: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:118: CASE
            {
                mCASE();


            }
            break;
            case 19: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:123: COMPONENT
            {
                mCOMPONENT();


            }
            break;
            case 20: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:133: CONFIGURATION
            {
                mCONFIGURATION();


            }
            break;
            case 21: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:147: CONSTANT
            {
                mCONSTANT();


            }
            break;
            case 22: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:156: DISCONNECT
            {
                mDISCONNECT();


            }
            break;
            case 23: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:167: DOWNTO
            {
                mDOWNTO();


            }
            break;
            case 24: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:174: ELSE
            {
                mELSE();


            }
            break;
            case 25: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:179: ELSIF
            {
                mELSIF();


            }
            break;
            case 26: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:185: END
            {
                mEND();


            }
            break;
            case 27: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:189: ENTITY
            {
                mENTITY();


            }
            break;
            case 28: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:196: EXIT
            {
                mEXIT();


            }
            break;
            case 29: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:201: FILE
            {
                mFILE();


            }
            break;
            case 30: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:206: FOR
            {
                mFOR();


            }
            break;
            case 31: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:210: FUNCTION
            {
                mFUNCTION();


            }
            break;
            case 32: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:219: GENERATE
            {
                mGENERATE();


            }
            break;
            case 33: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:228: GENERIC
            {
                mGENERIC();


            }
            break;
            case 34: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:236: GROUP
            {
                mGROUP();


            }
            break;
            case 35: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:242: GUARDED
            {
                mGUARDED();


            }
            break;
            case 36: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:250: IF
            {
                mIF();


            }
            break;
            case 37: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:253: IMPURE
            {
                mIMPURE();


            }
            break;
            case 38: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:260: IN
            {
                mIN();


            }
            break;
            case 39: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:263: INERTIAL
            {
                mINERTIAL();


            }
            break;
            case 40: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:272: INOUT
            {
                mINOUT();


            }
            break;
            case 41: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:278: IS
            {
                mIS();


            }
            break;
            case 42: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:281: LABEL
            {
                mLABEL();


            }
            break;
            case 43: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:287: LIBRARY
            {
                mLIBRARY();


            }
            break;
            case 44: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:295: LIMIT
            {
                mLIMIT();


            }
            break;
            case 45: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:301: LINKAGE
            {
                mLINKAGE();


            }
            break;
            case 46: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:309: LITERAL
            {
                mLITERAL();


            }
            break;
            case 47: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:317: LOOP
            {
                mLOOP();


            }
            break;
            case 48: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:322: MAP
            {
                mMAP();


            }
            break;
            case 49: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:326: MOD
            {
                mMOD();


            }
            break;
            case 50: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:330: NAND
            {
                mNAND();


            }
            break;
            case 51: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:335: NATURE
            {
                mNATURE();


            }
            break;
            case 52: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:342: NEW
            {
                mNEW();


            }
            break;
            case 53: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:346: NEXT
            {
                mNEXT();


            }
            break;
            case 54: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:351: NOISE
            {
                mNOISE();


            }
            break;
            case 55: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:357: NOR
            {
                mNOR();


            }
            break;
            case 56: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:361: NOT
            {
                mNEGATION_OPERATOR();


            }
            break;
            case 57: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:365: NULL
            {
                mNULL();


            }
            break;
            case 58: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:370: OF
            {
                mOF();


            }
            break;
            case 59: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:373: ON
            {
                mON();


            }
            break;
            case 60: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:376: OPEN
            {
                mOPEN();


            }
            break;
            case 61: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:381: OR
            {
                mOR();


            }
            break;
            case 62: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:384: OTHERS
            {
                mOTHERS();


            }
            break;
            case 63: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:391: OUT
            {
                mOUT();


            }
            break;
            case 64: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:395: PACKAGE
            {
                mPACKAGE();


            }
            break;
            case 65: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:403: PORT
            {
                mPORT();


            }
            break;
            case 66: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:408: POSTPONED
            {
                mPOSTPONED();


            }
            break;
            case 67: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:418: PROCEDURAL
            {
                mPROCEDURAL();


            }
            break;
            case 68: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:429: PROCEDURE
            {
                mPROCEDURE();


            }
            break;
            case 69: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:439: PROCESS
            {
                mPROCESS();


            }
            break;
            case 70: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:447: PURE
            {
                mPURE();


            }
            break;
            case 71: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:452: QUANTITY
            {
                mQUANTITY();


            }
            break;
            case 72: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:461: RANGE
            {
                mRANGE();


            }
            break;
            case 73: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:467: RECORD
            {
                mRECORD();


            }
            break;
            case 74: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:474: REFERENCE
            {
                mREFERENCE();


            }
            break;
            case 75: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:484: REGISTER
            {
                mREGISTER();


            }
            break;
            case 76: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:493: REJECT
            {
                mREJECT();


            }
            break;
            case 77: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:500: REM
            {
                mREM();


            }
            break;
            case 78: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:504: REPORT
            {
                mREPORT();


            }
            break;
            case 79: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:511: RETURN
            {
                mRETURN();


            }
            break;
            case 80: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:518: ROL
            {
                mROL();


            }
            break;
            case 81: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:522: ROR
            {
                mROR();


            }
            break;
            case 82: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:526: SELECT
            {
                mSELECT();


            }
            break;
            case 83: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:533: SEVERITY
            {
                mSEVERITY();


            }
            break;
            case 84: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:542: SHARED
            {
                mSHARED();


            }
            break;
            case 85: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:549: SIGNAL
            {
                mSIGNAL();


            }
            break;
            case 86: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:556: SLA
            {
                mSLA();


            }
            break;
            case 87: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:560: SLL
            {
                mSLL();


            }
            break;
            case 88: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:564: SPECTRUM
            {
                mSPECTRUM();


            }
            break;
            case 89: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:573: SRA
            {
                mSRA();


            }
            break;
            case 90: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:577: SRL
            {
                mSRL();


            }
            break;
            case 91: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:581: SUBNATURE
            {
                mSUBNATURE();


            }
            break;
            case 92: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:591: SUBTYPE
            {
                mSUBTYPE();


            }
            break;
            case 93: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:599: TERMINAL
            {
                mTERMINAL();


            }
            break;
            case 94: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:608: THEN
            {
                mTHEN();


            }
            break;
            case 95: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:613: THROUGH
            {
                mTHROUGH();


            }
            break;
            case 96: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:621: TO
            {
                mTO();


            }
            break;
            case 97: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:624: TOLERANCE
            {
                mTOLERANCE();


            }
            break;
            case 98: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:634: TRANSPORT
            {
                mTRANSPORT();


            }
            break;
            case 99: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:644: TYPE
            {
                mTYPE();


            }
            break;
            case 100: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:649: UNAFFECTED
            {
                mUNAFFECTED();


            }
            break;
            case 101: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:660: UNITS
            {
                mUNITS();


            }
            break;
            case 102: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:666: UNTIL
            {
                mUNTIL();


            }
            break;
            case 103: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:672: USE
            {
                mUSE();


            }
            break;
            case 104: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:676: VARIABLE
            {
                mVARIABLE();


            }
            break;
            case 105: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:685: WAIT
            {
                mWAIT();


            }
            break;
            case 106: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:690: WHEN
            {
                mWHEN();


            }
            break;
            case 107: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:695: WHILE
            {
                mWHILE();


            }
            break;
            case 108: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:701: WITH
            {
                mWITH();


            }
            break;
            case 109: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:706: XNOR
            {
                mXNOR();


            }
            break;
            case 110: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:711: XOR
            {
                mXOR();


            }
            break;
            case 111: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:715: INTEGER
            {
                mINTEGER();


            }
            break;
            case 112: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:723: LETTER
            {
                mLETTER();


            }
            break;
            case 113: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:730: DECIMAL_LITERAL
            {
                mDECIMAL_LITERAL();


            }
            break;
            case 114: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:746: BASIC_IDENTIFIER
            {
                mBASIC_IDENTIFIER();


            }
            break;
            case 115: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:763: EXTENDED_IDENTIFIER
            {
                mEXTENDED_IDENTIFIER();


            }
            break;
            case 116: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:783: COMMENT
            {
                mCOMMENT();


            }
            break;
            case 117: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:791: TAB
            {
                mTAB();


            }
            break;
            case 118: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:795: SPACE
            {
                mSPACE();


            }
            break;
            case 119: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:801: NEWLINE
            {
                mNEWLINE();


            }
            break;
            case 120: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:809: CHARACTER_LITERAL
            {
                mCHARACTER_LITERAL();


            }
            break;
            case 121: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:827: STRING_LITERAL
            {
                mSTRING_LITERAL();


            }
            break;
            case 122: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:842: OTHER_SPECIAL_CHARACTER
            {
                mOTHER_SPECIAL_CHARACTER();


            }
            break;
            case 123: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:866: DOUBLESTAR
            {
                mDOUBLESTAR();


            }
            break;
            case 124: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:877: ASSIGN
            {
                mEQUALITY_OPERATOR();


            }
            break;
            case 125: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:884: LE
            {
                mLESS_OR_EQUAL_OPERATOR();


            }
            break;
            case 126: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:887: GE
            {
                mGREATER_OR_EQUAL_OPERATOR();


            }
            break;
            case 127: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:890: ARROW
            {
                mARROW();


            }
            break;
            case 128: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:896: NEQ
            {
                mINEQUALITY_OPERATOR();


            }
            break;
            case 129: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:900: VARASGN
            {
                mVARASGN();


            }
            break;
            case 130: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:908: BOX
            {
                mBOX();


            }
            break;
            case 131: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:912: DBLQUOTE
            {
                mDBLQUOTE();


            }
            break;
            case 132: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:921: SEMI
            {
                mSEMI();


            }
            break;
            case 133: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:926: COMMA
            {
                mCOMMA();


            }
            break;
            case 134: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:932: AMPERSAND
            {
                mAMPERSAND();


            }
            break;
            case 135: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:942: LPAREN
            {
                mLPAREN();


            }
            break;
            case 136: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:949: RPAREN
            {
                mRPAREN();


            }
            break;
            case 137: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:956: LBRACKET
            {
                mLBRACKET();


            }
            break;
            case 138: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:965: RBRACKET
            {
                mRBRACKET();


            }
            break;
            case 139: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:974: COLON
            {
                mCOLON();


            }
            break;
            case 140: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:980: MUL
            {
                mMUL();


            }
            break;
            case 141: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:984: DIV
            {
                mDIV();


            }
            break;
            case 142: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:988: PLUS
            {
                mPLUS();


            }
            break;
            case 143: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:993: MINUS
            {
                mMINUS();


            }
            break;
            case 144: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:999: LOWERTHAN
            {
                mLOWER_THAN_OPERATOR();


            }
            break;
            case 145: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1009: GREATERTHAN
            {
                mGREATER_THAN_OPERATOR();


            }
            break;
            case 146: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1021: EQ
            {
                mEQ();


            }
            break;
            case 147: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1024: BAR
            {
                mBAR();


            }
            break;
            case 148: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1028: DOT
            {
                mDOT();


            }
            break;
            case 149: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1032: BACKSLASH
            {
                mBACKSLASH();


            }
            break;
            case 150: // D:\\WORK\\TESIS\\ANTLR\\GramÃ¡ticas\\vhdl.g:1:1042: EXPONENT
            {
                mEXPONENT();


            }
            break;
            default:
                consumeUntil(input, 1);                
                break;
                   
        }

    }
    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
            "\1\uffff\25\70\1\176\1\70\1\u0081\1\u0083\4\uffff\1\u0086\1\uffff"
            + "\1\u0088\1\u008b\1\u008e\1\u0090\1\u0092\1\u0094\12\uffff\10\71"
            + "\2\uffff\15\71\1\uffff\6\71\1\u00b6\1\71\1\u00ba\1\u00bb\11\71\1"
            + "\u00cc\1\u00cd\1\71\1\u00cf\23\71\1\u00f0\12\71\1\176\27\uffff\1"
            + "\u00fe\4\71\1\u0103\1\u0104\11\71\1\u010e\6\71\1\u0117\4\71\1\u011b"
            + "\4\71\1\uffff\3\71\2\uffff\6\71\1\u0129\1\u012a\2\71\1\u012d\2\71"
            + "\1\u0130\1\u0131\1\71\2\uffff\1\71\1\uffff\1\71\1\u0135\13\71\1"
            + "\u0141\2\71\1\u0144\1\u0145\4\71\1\u014a\1\u014b\1\71\1\u014d\1"
            + "\u014e\5\71\1\uffff\5\71\1\u015a\6\71\1\u0161\1\uffff\4\71\2\uffff"
            + "\6\71\1\u016c\2\71\1\uffff\1\u016f\5\71\1\u0175\1\71\1\uffff\1\71"
            + "\1\u0178\1\u0179\1\uffff\14\71\1\u0186\2\uffff\1\u0187\1\71\1\uffff"
            + "\1\u0189\1\71\2\uffff\1\u018b\1\u018c\1\71\1\uffff\1\71\1\u018f"
            + "\2\71\1\u0192\6\71\1\uffff\2\71\2\uffff\4\71\2\uffff\1\71\2\uffff"
            + "\3\71\1\u01a3\3\71\1\u01a7\3\71\1\uffff\1\71\1\u01ac\1\u01ad\1\71"
            + "\1\u01af\1\u01b0\1\uffff\2\71\1\u01b3\1\u01b4\1\71\1\u01b6\2\71"
            + "\1\u01b9\1\u01ba\1\uffff\1\u01bb\1\71\1\uffff\5\71\1\uffff\1\u01c2"
            + "\1\71\2\uffff\2\71\1\u01c7\3\71\1\u01cb\1\u01cc\1\71\1\u01ce\2\71"
            + "\2\uffff\1\71\1\uffff\1\u01d2\2\uffff\2\71\1\uffff\2\71\1\uffff"
            + "\1\71\1\u01d9\16\71\1\uffff\3\71\1\uffff\1\71\1\u01ec\1\u01ed\1"
            + "\71\2\uffff\1\u01ef\2\uffff\1\u01f0\1\u01f1\2\uffff\1\71\1\uffff"
            + "\1\u01f3\1\71\3\uffff\1\u01f5\4\71\1\u01fa\1\uffff\1\u01fb\3\71"
            + "\1\uffff\1\71\1\u0200\1\71\2\uffff\1\71\1\uffff\2\71\1\u0205\1\uffff"
            + "\1\u0206\5\71\1\uffff\1\u020c\2\71\1\u020f\1\u0210\1\u0211\1\u0212"
            + "\1\71\1\u0214\1\u0215\10\71\2\uffff\1\71\3\uffff\1\71\1\uffff\1"
            + "\71\1\uffff\4\71\2\uffff\2\71\1\u0227\1\u0228\1\uffff\1\71\1\u022a"
            + "\1\u022b\1\u022c\2\uffff\1\u022d\2\71\1\u0230\1\71\1\uffff\2\71"
            + "\4\uffff\1\71\2\uffff\2\71\1\u0237\1\71\1\u0239\10\71\1\u0242\1"
            + "\71\1\u0244\1\u0245\2\uffff\1\u0246\4\uffff\2\71\1\uffff\1\u024a"
            + "\1\71\1\u024c\1\u024d\1\u024e\1\71\1\uffff\1\u0250\1\uffff\3\71"
            + "\1\u0254\1\71\1\u0256\1\u0257\1\71\1\uffff\1\71\3\uffff\1\u025a"
            + "\1\71\1\u025c\1\uffff\1\u025d\3\uffff\1\u025e\1\uffff\1\u025f\1"
            + "\u0260\1\71\1\uffff\1\71\2\uffff\1\71\1\u0264\1\uffff\1\u0265\5"
            + "\uffff\1\u0266\2\71\3\uffff\1\u0269\1\71\1\uffff\1\u026b\1\uffff";
    static final String DFA12_eofS =
            "\u026c\uffff";
    static final String DFA12_minS =
            "\1\11\4\60\1\53\20\60\1\56\1\60\1\40\1\55\4\uffff\1\0\1\uffff\1"
            + "\52\5\75\12\uffff\1\163\1\143\1\164\1\151\1\144\1\143\1\163\1\164"
            + "\2\uffff\1\147\1\157\1\144\1\145\1\146\1\163\1\155\1\163\1\167\1"
            + "\163\1\144\1\151\1\60\1\uffff\1\154\1\162\2\156\1\157\1\141\1\60"
            + "\1\160\2\60\2\142\1\157\1\160\1\144\1\156\1\167\1\151\1\154\2\60"
            + "\1\145\1\60\1\150\1\164\1\143\1\162\1\157\1\162\1\141\1\156\1\143"
            + "\2\154\1\141\1\147\1\141\1\145\1\141\1\142\1\162\1\145\1\60\1\141"
            + "\1\160\1\141\1\145\1\162\1\151\1\145\1\164\1\157\1\162\1\56\27\uffff"
            + "\1\60\1\145\1\157\1\145\1\141\2\60\1\150\1\141\1\145\1\162\1\151"
            + "\1\143\1\171\1\141\1\146\1\60\1\145\1\160\1\146\1\143\1\156\1\145"
            + "\1\60\1\151\1\164\1\60\1\145\1\60\1\143\1\145\1\165\1\162\1\uffff"
            + "\1\165\1\162\1\165\2\uffff\1\145\1\162\1\151\1\153\1\145\1\160\2"
            + "\60\1\144\1\165\1\60\1\164\1\163\2\60\1\154\2\uffff\1\156\1\uffff"
            + "\1\145\1\60\1\153\2\164\1\143\1\145\1\156\1\147\1\157\1\145\1\151"
            + "\1\145\1\60\1\157\1\165\2\60\2\145\1\162\1\156\2\60\1\143\2\60\1"
            + "\156\1\155\1\156\1\157\1\145\1\uffff\1\156\1\145\1\146\1\164\1\151"
            + "\1\60\1\151\1\164\1\156\1\154\1\150\1\162\1\60\1\uffff\2\163\1\162"
            + "\1\163\2\uffff\1\151\1\171\1\162\1\151\1\156\1\153\1\60\1\153\1"
            + "\145\1\uffff\1\60\1\157\1\151\1\164\1\157\1\164\1\60\1\146\1\uffff"
            + "\1\164\2\60\1\uffff\1\164\1\162\1\160\1\144\1\162\2\164\1\154\1"
            + "\141\1\164\1\141\1\162\1\60\2\uffff\1\60\1\162\1\uffff\1\60\1\145"
            + "\2\uffff\2\60\1\162\1\uffff\1\141\1\60\1\160\1\145\1\60\1\164\1"
            + "\145\2\162\1\163\1\143\1\uffff\2\162\2\uffff\1\143\1\162\1\145\1"
            + "\141\2\uffff\1\164\2\uffff\1\141\1\171\1\151\1\60\1\165\1\162\1"
            + "\163\1\60\1\146\1\163\1\154\1\uffff\1\141\2\60\1\145\2\60\1\uffff"
            + "\2\163\2\60\1\164\1\60\1\164\1\142\2\60\1\uffff\1\60\1\162\1\uffff"
            + "\1\156\1\147\1\141\1\156\1\157\1\uffff\1\60\1\171\2\uffff\1\151"
            + "\1\141\1\60\2\145\1\151\2\60\1\162\1\60\1\147\1\141\2\uffff\1\145"
            + "\1\uffff\1\60\2\uffff\1\163\1\147\1\uffff\1\157\1\144\1\uffff\1"
            + "\151\1\60\1\144\1\145\3\164\1\156\1\164\1\151\1\144\1\154\1\162"
            + "\1\164\1\160\1\156\1\uffff\1\147\1\141\1\160\1\uffff\1\145\2\60"
            + "\1\142\2\uffff\1\60\2\uffff\2\60\2\uffff\1\145\1\uffff\1\60\1\165"
            + "\3\uffff\1\60\1\145\1\165\2\156\1\60\1\uffff\1\60\1\157\1\164\1"
            + "\143\1\uffff\1\144\1\60\1\141\2\uffff\1\171\1\uffff\1\145\1\154"
            + "\1\60\1\uffff\1\60\1\145\1\156\1\165\1\163\1\164\1\uffff\1\60\1"
            + "\156\1\145\4\60\1\164\2\60\2\165\1\145\1\141\1\150\1\156\1\157\1"
            + "\143\2\uffff\1\154\3\uffff\1\143\1\uffff\1\164\1\uffff\1\156\1\162"
            + "\1\164\1\145\2\uffff\1\156\1\145\2\60\1\uffff\1\154\3\60\2\uffff"
            + "\1\60\1\145\1\162\1\60\1\171\1\uffff\1\143\1\162\4\uffff\1\171\2"
            + "\uffff\1\155\1\162\1\60\1\154\1\60\1\143\1\162\1\164\1\145\1\164"
            + "\1\145\1\164\1\141\1\60\1\143\2\60\2\uffff\1\60\4\uffff\1\144\1"
            + "\141\1\uffff\1\60\1\145\3\60\1\145\1\uffff\1\60\1\uffff\1\145\1"
            + "\164\1\145\1\60\1\165\2\60\1\164\1\uffff\1\164\3\uffff\1\60\1\154"
            + "\1\60\1\uffff\1\60\3\uffff\1\60\1\uffff\2\60\1\144\1\uffff\1\162"
            + "\2\uffff\1\151\1\60\1\uffff\1\60\5\uffff\1\60\1\145\1\157\3\uffff"
            + "\1\60\1\156\1\uffff\1\60\1\uffff";
    static final String DFA12_maxS =
            "\1\u00ff\25\172\1\145\1\172\1\u00ff\1\55\4\uffff\1\uffff\1\uffff"
            + "\1\52\2\76\3\75\12\uffff\1\163\1\162\1\164\1\154\1\144\1\162\1\163"
            + "\1\164\2\uffff\1\147\1\157\1\144\1\145\2\163\1\156\1\163\1\167\1"
            + "\163\1\164\1\151\1\137\1\uffff\1\154\1\162\2\156\1\157\1\141\1\172"
            + "\1\160\2\172\1\142\1\164\1\157\1\160\1\144\1\164\1\170\1\164\1\154"
            + "\2\172\1\145\1\172\1\150\1\164\1\143\1\163\1\157\1\162\1\141\1\156"
            + "\1\164\1\162\1\166\1\141\1\147\1\154\1\145\1\154\1\142\2\162\1\172"
            + "\1\141\1\160\1\164\1\145\1\162\2\151\1\164\1\157\1\162\1\145\27"
            + "\uffff\1\172\1\145\1\157\1\145\1\141\2\172\1\150\1\141\1\145\1\162"
            + "\1\151\1\143\1\171\1\141\1\146\1\172\1\145\1\160\1\163\1\143\1\156"
            + "\1\151\1\172\1\151\1\164\1\137\1\145\1\172\1\143\1\145\1\165\1\162"
            + "\1\uffff\1\165\1\162\1\165\2\uffff\1\145\1\162\1\151\1\153\1\145"
            + "\1\160\2\172\1\144\1\165\1\172\1\164\1\163\2\172\1\154\2\uffff\1"
            + "\156\1\uffff\1\145\1\172\1\153\2\164\1\143\1\145\1\156\1\147\1\157"
            + "\1\145\1\151\1\145\1\172\1\157\1\165\2\172\2\145\1\162\1\156\2\172"
            + "\1\143\2\172\1\164\1\155\1\156\1\157\1\145\1\uffff\1\156\1\145\1"
            + "\146\1\164\1\151\1\172\1\151\1\164\1\156\1\154\1\150\1\162\1\172"
            + "\1\uffff\2\163\1\162\1\163\2\uffff\1\151\1\171\1\162\1\151\1\156"
            + "\1\153\1\172\1\153\1\145\1\uffff\1\172\1\157\1\151\1\164\1\157\1"
            + "\164\1\172\1\146\1\uffff\1\164\2\172\1\uffff\1\164\1\162\1\160\1"
            + "\144\1\162\2\164\1\154\1\141\1\164\1\141\1\162\1\172\2\uffff\1\172"
            + "\1\162\1\uffff\1\172\1\145\2\uffff\2\172\1\162\1\uffff\1\141\1\172"
            + "\1\160\1\145\1\172\1\164\1\145\2\162\1\163\1\143\1\uffff\2\162\2"
            + "\uffff\1\143\1\162\1\145\1\141\2\uffff\1\164\2\uffff\1\141\1\171"
            + "\1\151\1\172\1\165\1\162\1\163\1\172\1\146\1\163\1\154\1\uffff\1"
            + "\141\2\172\1\145\2\172\1\uffff\2\163\2\172\1\164\1\172\1\164\1\142"
            + "\2\172\1\uffff\1\172\1\162\1\uffff\1\156\1\147\1\141\1\156\1\157"
            + "\1\uffff\1\172\1\171\2\uffff\2\151\1\172\2\145\1\151\2\172\1\162"
            + "\1\172\1\147\1\141\2\uffff\1\145\1\uffff\1\172\2\uffff\1\163\1\147"
            + "\1\uffff\1\157\1\163\1\uffff\1\151\1\172\1\144\1\145\3\164\1\156"
            + "\1\164\1\151\1\144\1\154\1\162\1\164\1\160\1\156\1\uffff\1\147\1"
            + "\141\1\160\1\uffff\1\145\2\172\1\142\2\uffff\1\172\2\uffff\2\172"
            + "\2\uffff\1\145\1\uffff\1\172\1\165\3\uffff\1\172\1\145\1\165\2\156"
            + "\1\172\1\uffff\1\172\1\157\1\164\1\143\1\uffff\1\144\1\172\1\141"
            + "\2\uffff\1\171\1\uffff\1\145\1\154\1\172\1\uffff\1\172\1\145\1\156"
            + "\1\165\1\163\1\164\1\uffff\1\172\1\156\1\145\4\172\1\164\2\172\2"
            + "\165\1\145\1\141\1\150\1\156\1\157\1\143\2\uffff\1\154\3\uffff\1"
            + "\143\1\uffff\1\164\1\uffff\1\156\1\162\1\164\1\145\2\uffff\1\156"
            + "\1\145\2\172\1\uffff\1\154\3\172\2\uffff\1\172\1\145\1\162\1\172"
            + "\1\171\1\uffff\1\143\1\162\4\uffff\1\171\2\uffff\1\155\1\162\1\172"
            + "\1\154\1\172\1\143\1\162\1\164\1\145\1\164\1\145\1\164\1\141\1\172"
            + "\1\143\2\172\2\uffff\1\172\4\uffff\1\144\1\145\1\uffff\1\172\1\145"
            + "\3\172\1\145\1\uffff\1\172\1\uffff\1\145\1\164\1\145\1\172\1\165"
            + "\2\172\1\164\1\uffff\1\164\3\uffff\1\172\1\154\1\172\1\uffff\1\172"
            + "\3\uffff\1\172\1\uffff\2\172\1\144\1\uffff\1\162\2\uffff\1\151\1"
            + "\172\1\uffff\1\172\5\uffff\1\172\1\145\1\157\3\uffff\1\172\1\156"
            + "\1\uffff\1\172\1\uffff";
    static final String DFA12_acceptS =
            "\32\uffff\1\165\1\166\1\167\1\170\1\uffff\1\172\6\uffff\1\u0084"
            + "\1\u0085\1\u0086\1\u0087\1\u0088\1\u0089\1\u008a\1\u008e\1\u0093"
            + "\1\u0094\10\uffff\1\160\1\162\15\uffff\1\u0096\66\uffff\1\157\1"
            + "\161\1\163\1\u0095\1\164\1\u008f\1\166\1\171\1\u0083\1\173\1\u008c"
            + "\1\174\1\177\1\u0092\1\175\1\u0082\1\u0090\1\176\1\u0091\1\u0080"
            + "\1\u008d\1\u0081\1\u008b\41\uffff\1\44\3\uffff\1\46\1\51\20\uffff"
            + "\1\72\1\73\1\uffff\1\75\40\uffff\1\140\15\uffff\1\1\4\uffff\1\6"
            + "\1\7\11\uffff\1\21\10\uffff\1\32\3\uffff\1\36\15\uffff\1\60\1\61"
            + "\2\uffff\1\64\2\uffff\1\67\1\70\3\uffff\1\77\13\uffff\1\115\2\uffff"
            + "\1\120\1\121\4\uffff\1\126\1\127\1\uffff\1\131\1\132\13\uffff\1"
            + "\147\6\uffff\1\156\12\uffff\1\16\2\uffff\1\22\5\uffff\1\30\2\uffff"
            + "\1\34\1\35\14\uffff\1\57\1\62\1\uffff\1\65\1\uffff\1\71\1\74\2\uffff"
            + "\1\101\2\uffff\1\106\20\uffff\1\136\3\uffff\1\143\4\uffff\1\151"
            + "\1\152\1\uffff\1\154\1\155\2\uffff\1\4\1\5\1\uffff\1\11\2\uffff"
            + "\1\14\1\15\1\17\6\uffff\1\31\4\uffff\1\42\3\uffff\1\50\1\52\1\uffff"
            + "\1\54\3\uffff\1\66\6\uffff\1\110\22\uffff\1\145\1\146\1\uffff\1"
            + "\153\1\2\1\3\1\uffff\1\12\1\uffff\1\20\4\uffff\1\27\1\33\4\uffff"
            + "\1\45\4\uffff\1\63\1\76\5\uffff\1\111\2\uffff\1\114\1\116\1\117"
            + "\1\122\1\uffff\1\124\1\125\21\uffff\1\41\1\43\1\uffff\1\53\1\55"
            + "\1\56\1\100\2\uffff\1\105\6\uffff\1\134\1\uffff\1\137\10\uffff\1"
            + "\25\1\uffff\1\37\1\40\1\47\3\uffff\1\107\1\uffff\1\113\1\123\1\130"
            + "\1\uffff\1\135\3\uffff\1\150\1\uffff\1\13\1\23\2\uffff\1\102\1\uffff"
            + "\1\104\1\112\1\133\1\141\1\142\3\uffff\1\26\1\103\1\144\2\uffff"
            + "\1\10\1\uffff\1\24";
    static final String DFA12_specialS =
            "\36\uffff\1\0\u024d\uffff}>";
    static final String[] DFA12_transitionS = {
        "\1\32\1\34\25\uffff\1\33\1\37\1\36\1\uffff\2\37\1\50\1\35\1"
        + "\51\1\52\1\40\1\55\1\47\1\31\1\57\1\44\12\26\1\45\1\46\1\42"
        + "\1\41\1\43\2\37\32\27\1\53\1\30\1\54\1\37\1\uffff\1\37\1\1\1"
        + "\2\1\3\1\4\1\5\1\6\1\7\1\27\1\10\2\27\1\11\1\12\1\13\1\14\1"
        + "\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\2\27\1\37\1\56\2"
        + "\37\42\uffff\137\37",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\71\1\60\1\61\2"
        + "\71\1\62\5\71\1\63\1\71\1\64\3\71\1\65\1\66\1\67\6\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\4\71\1\72\6\71\1"
        + "\73\2\71\1\74\2\71\1\75\2\71\1\76\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\77\15\71\1\100"
        + "\13\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\10\71\1\101\5\71"
        + "\1\102\13\71",
        "\1\107\1\uffff\1\107\2\uffff\12\106\7\uffff\32\71\4\uffff\1"
        + "\71\1\uffff\13\71\1\103\1\71\1\104\11\71\1\105\2\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\10\71\1\110\5\71"
        + "\1\111\5\71\1\112\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\4\71\1\113\14\71"
        + "\1\114\2\71\1\115\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\5\71\1\116\6\71\1"
        + "\117\1\120\4\71\1\121\7\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\122\7\71\1\123"
        + "\5\71\1\124\13\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\125\15\71\1\126"
        + "\13\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\127\3\71\1\130"
        + "\11\71\1\131\5\71\1\132\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\5\71\1\133\7\71\1"
        + "\134\1\71\1\135\1\71\1\136\1\71\1\137\1\140\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\141\15\71\1\142"
        + "\2\71\1\143\2\71\1\144\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\24\71\1\145\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\146\3\71\1\147"
        + "\11\71\1\150\13\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\4\71\1\151\2\71\1"
        + "\152\1\153\2\71\1\154\3\71\1\155\1\71\1\156\2\71\1\157\5\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\4\71\1\160\2\71\1"
        + "\161\6\71\1\162\2\71\1\163\6\71\1\164\1\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\15\71\1\165\4\71"
        + "\1\166\7\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\167\31\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\1\170\6\71\1\171"
        + "\1\172\21\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\15\71\1\173\1\174"
        + "\13\71",
        "\1\177\1\uffff\12\175\45\uffff\1\175\5\uffff\1\177",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\2\u0080\1\uffff\7\u0080\1\uffff\5\u0080\12\uffff\7\u0080\32"
        + "\uffff\6\u0080\32\uffff\4\u0080\42\uffff\137\u0080",
        "\1\u0082",
        "",
        "",
        "",
        "",
        "\0\u0085",
        "",
        "\1\u0087",
        "\1\u0089\1\u008a",
        "\1\u008c\1\u008d",
        "\1\u008f",
        "\1\u0091",
        "\1\u0093",
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
        "\1\u0095",
        "\1\u0096\16\uffff\1\u0097",
        "\1\u0098",
        "\1\u0099\2\uffff\1\u009a",
        "\1\u009b",
        "\1\u009c\16\uffff\1\u009d",
        "\1\u009e",
        "\1\u009f",
        "",
        "",
        "\1\u00a0",
        "\1\u00a1",
        "\1\u00a2",
        "\1\u00a3",
        "\1\u00a4\14\uffff\1\u00a5",
        "\1\u00a6",
        "\1\u00a7\1\u00a8",
        "\1\u00a9",
        "\1\u00aa",
        "\1\u00ab",
        "\1\u00ac\17\uffff\1\u00ad",
        "\1\u00ae",
        "\12\u00af\45\uffff\1\u00af",
        "",
        "\1\u00b0",
        "\1\u00b1",
        "\1\u00b2",
        "\1\u00b3",
        "\1\u00b4",
        "\1\u00b5",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u00b7",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\4\71\1\u00b8\11\71"
        + "\1\u00b9\13\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u00bc",
        "\1\u00bd\12\uffff\1\u00be\1\u00bf\5\uffff\1\u00c0",
        "\1\u00c1",
        "\1\u00c2",
        "\1\u00c3",
        "\1\u00c4\5\uffff\1\u00c5",
        "\1\u00c6\1\u00c7",
        "\1\u00c8\10\uffff\1\u00c9\1\uffff\1\u00ca",
        "\1\u00cb",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u00ce",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u00d0",
        "\1\u00d1",
        "\1\u00d2",
        "\1\u00d3\1\u00d4",
        "\1\u00d5",
        "\1\u00d6",
        "\1\u00d7",
        "\1\u00d8",
        "\1\u00d9\2\uffff\1\u00da\1\u00db\2\uffff\1\u00dc\2\uffff\1"
        + "\u00dd\2\uffff\1\u00de\3\uffff\1\u00df",
        "\1\u00e0\5\uffff\1\u00e1",
        "\1\u00e2\11\uffff\1\u00e3",
        "\1\u00e4",
        "\1\u00e5",
        "\1\u00e6\12\uffff\1\u00e7",
        "\1\u00e8",
        "\1\u00e9\12\uffff\1\u00ea",
        "\1\u00eb",
        "\1\u00ec",
        "\1\u00ed\14\uffff\1\u00ee",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\13\71\1\u00ef\16"
        + "\71",
        "\1\u00f1",
        "\1\u00f2",
        "\1\u00f3\7\uffff\1\u00f4\12\uffff\1\u00f5",
        "\1\u00f6",
        "\1\u00f7",
        "\1\u00f8",
        "\1\u00f9\3\uffff\1\u00fa",
        "\1\u00fb",
        "\1\u00fc",
        "\1\u00fd",
        "\1\177\1\uffff\12\175\45\uffff\1\175\5\uffff\1\177",
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
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u00ff",
        "\1\u0100",
        "\1\u0101",
        "\1\u0102",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0105",
        "\1\u0106",
        "\1\u0107",
        "\1\u0108",
        "\1\u0109",
        "\1\u010a",
        "\1\u010b",
        "\1\u010c",
        "\1\u010d",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u010f",
        "\1\u0110",
        "\1\u0111\14\uffff\1\u0112",
        "\1\u0113",
        "\1\u0114",
        "\1\u0115\3\uffff\1\u0116",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0118",
        "\1\u0119",
        "\12\u00af\45\uffff\1\u00af",
        "\1\u011a",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u011c",
        "\1\u011d",
        "\1\u011e",
        "\1\u011f",
        "",
        "\1\u0120",
        "\1\u0121",
        "\1\u0122",
        "",
        "",
        "\1\u0123",
        "\1\u0124",
        "\1\u0125",
        "\1\u0126",
        "\1\u0127",
        "\1\u0128",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u012b",
        "\1\u012c",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u012e",
        "\1\u012f",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0132",
        "",
        "",
        "\1\u0133",
        "",
        "\1\u0134",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
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
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0142",
        "\1\u0143",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0146",
        "\1\u0147",
        "\1\u0148",
        "\1\u0149",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u014c",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u014f\5\uffff\1\u0150",
        "\1\u0151",
        "\1\u0152",
        "\1\u0153",
        "\1\u0154",
        "",
        "\1\u0155",
        "\1\u0156",
        "\1\u0157",
        "\1\u0158",
        "\1\u0159",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u015b",
        "\1\u015c",
        "\1\u015d",
        "\1\u015e",
        "\1\u015f",
        "\1\u0160",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\1\u0162",
        "\1\u0163",
        "\1\u0164",
        "\1\u0165",
        "",
        "",
        "\1\u0166",
        "\1\u0167",
        "\1\u0168",
        "\1\u0169",
        "\1\u016a",
        "\1\u016b",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u016d",
        "\1\u016e",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0170",
        "\1\u0171",
        "\1\u0172",
        "\1\u0173",
        "\1\u0174",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0176",
        "",
        "\1\u0177",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\1\u017a",
        "\1\u017b",
        "\1\u017c",
        "\1\u017d",
        "\1\u017e",
        "\1\u017f",
        "\1\u0180",
        "\1\u0181",
        "\1\u0182",
        "\1\u0183",
        "\1\u0184",
        "\1\u0185",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0188",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u018a",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u018d",
        "",
        "\1\u018e",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0190",
        "\1\u0191",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0193",
        "\1\u0194",
        "\1\u0195",
        "\1\u0196",
        "\1\u0197",
        "\1\u0198",
        "",
        "\1\u0199",
        "\1\u019a",
        "",
        "",
        "\1\u019b",
        "\1\u019c",
        "\1\u019d",
        "\1\u019e",
        "",
        "",
        "\1\u019f",
        "",
        "",
        "\1\u01a0",
        "\1\u01a1",
        "\1\u01a2",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01a4",
        "\1\u01a5",
        "\1\u01a6",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01a8",
        "\1\u01a9",
        "\1\u01aa",
        "",
        "\1\u01ab",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01ae",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\1\u01b1",
        "\1\u01b2",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01b5",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01b7",
        "\1\u01b8",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01bc",
        "",
        "\1\u01bd",
        "\1\u01be",
        "\1\u01bf",
        "\1\u01c0",
        "\1\u01c1",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01c3",
        "",
        "",
        "\1\u01c4",
        "\1\u01c5\7\uffff\1\u01c6",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01c8",
        "\1\u01c9",
        "\1\u01ca",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01cd",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01cf",
        "\1\u01d0",
        "",
        "",
        "\1\u01d1",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\1\u01d3",
        "\1\u01d4",
        "",
        "\1\u01d5",
        "\1\u01d6\16\uffff\1\u01d7",
        "",
        "\1\u01d8",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01da",
        "\1\u01db",
        "\1\u01dc",
        "\1\u01dd",
        "\1\u01de",
        "\1\u01df",
        "\1\u01e0",
        "\1\u01e1",
        "\1\u01e2",
        "\1\u01e3",
        "\1\u01e4",
        "\1\u01e5",
        "\1\u01e6",
        "\1\u01e7",
        "",
        "\1\u01e8",
        "\1\u01e9",
        "\1\u01ea",
        "",
        "\1\u01eb",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01ee",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\1\u01f2",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01f4",
        "",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01f6",
        "\1\u01f7",
        "\1\u01f8",
        "\1\u01f9",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u01fc",
        "\1\u01fd",
        "\1\u01fe",
        "",
        "\1\u01ff",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0201",
        "",
        "",
        "\1\u0202",
        "",
        "\1\u0203",
        "\1\u0204",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0207",
        "\1\u0208",
        "\1\u0209",
        "\1\u020a",
        "\1\u020b",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u020d",
        "\1\u020e",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0213",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0216",
        "\1\u0217",
        "\1\u0218",
        "\1\u0219",
        "\1\u021a",
        "\1\u021b",
        "\1\u021c",
        "\1\u021d",
        "",
        "",
        "\1\u021e",
        "",
        "",
        "",
        "\1\u021f",
        "",
        "\1\u0220",
        "",
        "\1\u0221",
        "\1\u0222",
        "\1\u0223",
        "\1\u0224",
        "",
        "",
        "\1\u0225",
        "\1\u0226",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\1\u0229",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u022e",
        "\1\u022f",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0231",
        "",
        "\1\u0232",
        "\1\u0233",
        "",
        "",
        "",
        "",
        "\1\u0234",
        "",
        "",
        "\1\u0235",
        "\1\u0236",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0238",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u023a",
        "\1\u023b",
        "\1\u023c",
        "\1\u023d",
        "\1\u023e",
        "\1\u023f",
        "\1\u0240",
        "\1\u0241",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0243",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "",
        "",
        "\1\u0247",
        "\1\u0248\3\uffff\1\u0249",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u024b",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u024f",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\1\u0251",
        "\1\u0252",
        "\1\u0253",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0255",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0258",
        "",
        "\1\u0259",
        "",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u025b",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0261",
        "",
        "\1\u0262",
        "",
        "",
        "\1\u0263",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "",
        "",
        "",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u0267",
        "\1\u0268",
        "",
        "",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        "\1\u026a",
        "",
        "\12\71\7\uffff\32\71\4\uffff\1\71\1\uffff\32\71",
        ""
    };
    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }

        public String getDescription() {
            return "1:1: Tokens : ( ABS | ACCESS | ACROSS | AFTER | ALIAS | ALL | AND | ARCHITECTURE | ARRAY | ASSERT | ATTRIBUTE | BEGIN | BLOCK | BODY | BREAK | BUFFER | BUS | CASE | COMPONENT | CONFIGURATION | CONSTANT | DISCONNECT | DOWNTO | ELSE | ELSIF | END | ENTITY | EXIT | FILE | FOR | FUNCTION | GENERATE | GENERIC | GROUP | GUARDED | IF | IMPURE | IN | INERTIAL | INOUT | IS | LABEL | LIBRARY | LIMIT | LINKAGE | LITERAL | LOOP | MAP | MOD | NAND | NATURE | NEW | NEXT | NOISE | NOR | NOT | NULL | OF | ON | OPEN | OR | OTHERS | OUT | PACKAGE | PORT | POSTPONED | PROCEDURAL | PROCEDURE | PROCESS | PURE | QUANTITY | RANGE | RECORD | REFERENCE | REGISTER | REJECT | REM | REPORT | RETURN | ROL | ROR | SELECT | SEVERITY | SHARED | SIGNAL | SLA | SLL | SPECTRUM | SRA | SRL | SUBNATURE | SUBTYPE | TERMINAL | THEN | THROUGH | TO | TOLERANCE | TRANSPORT | TYPE | UNAFFECTED | UNITS | UNTIL | USE | VARIABLE | WAIT | WHEN | WHILE | WITH | XNOR | XOR | INTEGER | LETTER | DECIMAL_LITERAL | BASIC_IDENTIFIER | EXTENDED_IDENTIFIER | COMMENT | TAB | SPACE | NEWLINE | CHARACTER_LITERAL | STRING_LITERAL | OTHER_SPECIAL_CHARACTER | DOUBLESTAR | ASSIGN | LE | GE | ARROW | NEQ | VARASGN | BOX | DBLQUOTE | SEMI | COMMA | AMPERSAND | LPAREN | RPAREN | LBRACKET | RBRACKET | COLON | MUL | DIV | PLUS | MINUS | LOWERTHAN | GREATERTHAN | EQ | BAR | DOT | BACKSLASH | EXPONENT );";
        }

        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
            int _s = s;
            switch (s) {
                case 0:
                    int LA12_30 = input.LA(1);

                    s = -1;
                    if (((LA12_30 >= '\u0000' && LA12_30 <= '\uFFFF'))) {
                        s = 133;
                    } else {
                        s = 134;
                    }

                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            NoViableAltException nvae =
                    new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
}