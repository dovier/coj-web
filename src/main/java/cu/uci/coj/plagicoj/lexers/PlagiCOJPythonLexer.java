/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.plagicoj.lexers;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class PlagiCOJPythonLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int FOR=300;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int IF=350;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int NEGATION_OPERATOR=106;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int WHILE=301;
    public static final int T__92=92;
    public static final int ALT_NOTEQUAL=4;
    public static final int AMPER=5;
    public static final int AMPEREQUAL=6;
    public static final int ASSIGN=7;
    public static final int BACKQUOTE=8;
    public static final int CIRCUMFLEX=9;
    public static final int CIRCUMFLEXEQUAL=10;
    public static final int COLON=11;
    public static final int COMMA=12;
    public static final int COMMENT=13;
    public static final int COMPLEX=14;
    public static final int CONTINUED_LINE=15;
    public static final int DEDENT=16;
    public static final int DIGITS=17;
    public static final int DOT=18;
    public static final int DOUBLESLASH=19;
    public static final int DOUBLESLASHEQUAL=20;
    public static final int DOUBLESTAR=21;
    public static final int DOUBLESTAREQUAL=22;
    public static final int EQUALITY_OPERATOR=100;
    public static final int ESC=24;
    public static final int Exponent=25;
    public static final int FLOAT=26;
    public static final int GREATER_THAN_OPERATOR=103;
    public static final int GREATER_OR_EQUAL_OPERATOR=105;
    public static final int INDENT=29;
    public static final int INT=30;
    public static final int LBRACK=31;
    public static final int LCURLY=32;
    public static final int LEADING_WS=33;
    public static final int LEFTSHIFT=34;
    public static final int LEFTSHIFTEQUAL=35;
    public static final int LOWER_THAN_OPERATOR=102;
    public static final int LESS_OR_EQUAL_OPERATOR=104;
    public static final int LONGINT=38;
    public static final int LPAREN=39;
    public static final int MINUS=40;
    public static final int MINUSEQUAL=41;
    public static final int NAME=42;
    public static final int NEWLINE=43;
    public static final int INEQUALITY_OPERATOR=101;
    public static final int PERCENT=45;
    public static final int PERCENTEQUAL=46;
    public static final int PLUS=47;
    public static final int PLUSEQUAL=48;
    public static final int RBRACK=49;
    public static final int RCURLY=50;
    public static final int RIGHTSHIFT=51;
    public static final int RIGHTSHIFTEQUAL=52;
    public static final int RPAREN=53;
    public static final int SEMI=54;
    public static final int SLASH=55;
    public static final int SLASHEQUAL=56;
    public static final int STAR=57;
    public static final int STAREQUAL=58;
    public static final int STRING=59;
    public static final int TILDE=60;
    public static final int VBAR=61;
    public static final int VBAREQUAL=62;
    public static final int WS=63;

    /** Handles context-sensitive lexing of implicit line joining such as
     *  the case where newline is ignored in cases like this:
     *  a = [3,
     *       4]
     */
    int implicitLineJoiningLevel = 0;
    int startPos=-1;


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public PlagiCOJPythonLexer() {} 
    public PlagiCOJPythonLexer(CharStream input) {
        super(input);
    }
 
    public String getGrammarFileName() { return "D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g"; }

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:12:7: ( 'and' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:12:9: 'and'
            {
            match("and"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:13:7: ( 'assert' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:13:9: 'assert'
            {
            match("assert"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:14:7: ( 'break' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:14:9: 'break'
            {
            match("break"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:15:7: ( 'class' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:15:9: 'class'
            {
            match("class"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:16:7: ( 'continue' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:16:9: 'continue'
            {
            match("continue"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:17:7: ( 'def' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:17:9: 'def'
            {
            match("def"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:18:7: ( 'del' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:18:9: 'del'
            {
            match("del"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:19:7: ( 'elif' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:19:9: 'elif'
            {
            match("elif"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:20:7: ( 'else' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:20:9: 'else'
            {
            match("else"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:21:7: ( 'except' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:21:9: 'except'
            {
            match("except"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:22:7: ( 'exec' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:22:9: 'exec'
            {
            match("exec"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:23:7: ( 'finally' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:23:9: 'finally'
            {
            match("finally"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:24:7: ( 'for' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:24:9: 'for'
            {
            match("for"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:25:7: ( 'from' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:25:9: 'from'
            {
            match("from"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:26:7: ( 'global' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:26:9: 'global'
            {
            match("global"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:27:7: ( 'if' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:27:9: 'if'
            {
            match("if"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:28:7: ( 'import' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:28:9: 'import'
            {
            match("import"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:29:7: ( 'in' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:29:9: 'in'
            {
            match("in"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:30:7: ( 'is' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:30:9: 'is'
            {
            match("is"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:31:7: ( 'lambda' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:31:9: 'lambda'
            {
            match("lambda"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mNEGATION_OPERATOR() throws RecognitionException {
        try {
            int _type = NEGATION_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:32:7: ( 'not' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:32:9: 'not'
            {
            match("not"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:33:7: ( 'or' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:33:9: 'or'
            {
            match("or"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:34:7: ( 'pass' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:34:9: 'pass'
            {
            match("pass"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:35:7: ( 'print' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:35:9: 'print'
            {
            match("print"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:36:7: ( 'raise' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:36:9: 'raise'
            {
            match("raise"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:37:7: ( 'return' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:37:9: 'return'
            {
            match("return"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:38:7: ( 'try' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:38:9: 'try'
            {
            match("try"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
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
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:40:7: ( 'yield' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:40:9: 'yield'
            {
            match("yield"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:389:8: ( '(' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:389:10: '('
            {
            match('('); 

            implicitLineJoiningLevel++;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:391:8: ( ')' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:391:10: ')'
            {
            match(')'); 

            implicitLineJoiningLevel--;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACK"
    public final void mLBRACK() throws RecognitionException {
        try {
            int _type = LBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:393:8: ( '[' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:393:10: '['
            {
            match('['); 

            implicitLineJoiningLevel++;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LBRACK"

    // $ANTLR start "RBRACK"
    public final void mRBRACK() throws RecognitionException {
        try {
            int _type = RBRACK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:395:8: ( ']' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:395:10: ']'
            {
            match(']'); 

            implicitLineJoiningLevel--;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "RBRACK"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:397:8: ( ':' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:397:10: ':'
            {
            match(':'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:399:7: ( ',' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:399:9: ','
            {
            match(','); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:401:6: ( ';' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:401:8: ';'
            {
            match(';'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:403:6: ( '+' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:403:8: '+'
            {
            match('+'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:405:7: ( '-' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:405:9: '-'
            {
            match('-'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:407:6: ( '*' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:407:8: '*'
            {
            match('*'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "SLASH"
    public final void mSLASH() throws RecognitionException {
        try {
            int _type = SLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:409:7: ( '/' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:409:9: '/'
            {
            match('/'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "SLASH"

    // $ANTLR start "VBAR"
    public final void mVBAR() throws RecognitionException {
        try {
            int _type = VBAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:411:6: ( '|' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:411:8: '|'
            {
            match('|'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "VBAR"

    // $ANTLR start "AMPER"
    public final void mAMPER() throws RecognitionException {
        try {
            int _type = AMPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:413:7: ( '&' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:413:9: '&'
            {
            match('&'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "AMPER"

    // $ANTLR start "LESS"
    public final void mLOWER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = LOWER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:415:6: ( '<' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:415:8: '<'
            {
            match('<'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LESS"

    // $ANTLR start "GREATER"
    public final void mGREATER_THAN_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_THAN_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:417:9: ( '>' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:417:11: '>'
            {
            match('>'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "GREATER"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:419:8: ( '=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:419:10: '='
            {
            match('='); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:421:9: ( '%' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:421:11: '%'
            {
            match('%'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "BACKQUOTE"
    public final void mBACKQUOTE() throws RecognitionException {
        try {
            int _type = BACKQUOTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:423:11: ( '`' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:423:13: '`'
            {
            match('`'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "BACKQUOTE"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:425:8: ( '{' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:425:10: '{'
            {
            match('{'); 

            implicitLineJoiningLevel++;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:427:8: ( '}' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:427:10: '}'
            {
            match('}'); 

            implicitLineJoiningLevel--;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "CIRCUMFLEX"
    public final void mCIRCUMFLEX() throws RecognitionException {
        try {
            int _type = CIRCUMFLEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:429:12: ( '^' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:429:14: '^'
            {
            match('^'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "CIRCUMFLEX"

    // $ANTLR start "TILDE"
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:431:7: ( '~' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:431:9: '~'
            {
            match('~'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "TILDE"

    // $ANTLR start "EQUAL"
    public final void mEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = EQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:433:7: ( '==' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:433:9: '=='
            {
            match("=="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "EQUAL"

    // $ANTLR start "NOTEQUAL"
    public final void mINEQUALITY_OPERATOR() throws RecognitionException {
        try {
            int _type = INEQUALITY_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:435:10: ( '!=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:435:12: '!='
            {
            match("!="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "NOTEQUAL"

    // $ANTLR start "ALT_NOTEQUAL"
    public final void mALT_NOTEQUAL() throws RecognitionException {
        try {
            int _type = ALT_NOTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:437:13: ( '<>' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:437:15: '<>'
            {
            match("<>"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "ALT_NOTEQUAL"

    // $ANTLR start "LESSEQUAL"
    public final void mLESS_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = LESS_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:439:11: ( '<=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:439:13: '<='
            {
            match("<="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LESSEQUAL"

    // $ANTLR start "LEFTSHIFT"
    public final void mLEFTSHIFT() throws RecognitionException {
        try {
            int _type = LEFTSHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:441:11: ( '<<' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:441:13: '<<'
            {
            match("<<"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LEFTSHIFT"

    // $ANTLR start "GREATEREQUAL"
    public final void mGREATER_OR_EQUAL_OPERATOR() throws RecognitionException {
        try {
            int _type = GREATER_OR_EQUAL_OPERATOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:443:14: ( '>=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:443:16: '>='
            {
            match(">="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "GREATEREQUAL"

    // $ANTLR start "RIGHTSHIFT"
    public final void mRIGHTSHIFT() throws RecognitionException {
        try {
            int _type = RIGHTSHIFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:445:12: ( '>>' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:445:14: '>>'
            {
            match(">>"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "RIGHTSHIFT"

    // $ANTLR start "PLUSEQUAL"
    public final void mPLUSEQUAL() throws RecognitionException {
        try {
            int _type = PLUSEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:447:11: ( '+=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:447:13: '+='
            {
            match("+="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "PLUSEQUAL"

    // $ANTLR start "MINUSEQUAL"
    public final void mMINUSEQUAL() throws RecognitionException {
        try {
            int _type = MINUSEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:449:12: ( '-=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:449:14: '-='
            {
            match("-="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "MINUSEQUAL"

    // $ANTLR start "DOUBLESTAR"
    public final void mDOUBLESTAR() throws RecognitionException {
        try {
            int _type = DOUBLESTAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:451:12: ( '**' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:451:14: '**'
            {
            match("**"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "DOUBLESTAR"

    // $ANTLR start "STAREQUAL"
    public final void mSTAREQUAL() throws RecognitionException {
        try {
            int _type = STAREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:453:11: ( '*=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:453:13: '*='
            {
            match("*="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "STAREQUAL"

    // $ANTLR start "DOUBLESLASH"
    public final void mDOUBLESLASH() throws RecognitionException {
        try {
            int _type = DOUBLESLASH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:455:13: ( '//' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:455:15: '//'
            {
            match("//"); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "DOUBLESLASH"

    // $ANTLR start "SLASHEQUAL"
    public final void mSLASHEQUAL() throws RecognitionException {
        try {
            int _type = SLASHEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:457:12: ( '/=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:457:14: '/='
            {
            match("/="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "SLASHEQUAL"

    // $ANTLR start "VBAREQUAL"
    public final void mVBAREQUAL() throws RecognitionException {
        try {
            int _type = VBAREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:459:11: ( '|=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:459:13: '|='
            {
            match("|="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "VBAREQUAL"

    // $ANTLR start "PERCENTEQUAL"
    public final void mPERCENTEQUAL() throws RecognitionException {
        try {
            int _type = PERCENTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:461:14: ( '%=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:461:16: '%='
            {
            match("%="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "PERCENTEQUAL"

    // $ANTLR start "AMPEREQUAL"
    public final void mAMPEREQUAL() throws RecognitionException {
        try {
            int _type = AMPEREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:463:12: ( '&=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:463:14: '&='
            {
            match("&="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "AMPEREQUAL"

    // $ANTLR start "CIRCUMFLEXEQUAL"
    public final void mCIRCUMFLEXEQUAL() throws RecognitionException {
        try {
            int _type = CIRCUMFLEXEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:465:17: ( '^=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:465:19: '^='
            {
            match("^="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "CIRCUMFLEXEQUAL"

    // $ANTLR start "LEFTSHIFTEQUAL"
    public final void mLEFTSHIFTEQUAL() throws RecognitionException {
        try {
            int _type = LEFTSHIFTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:467:16: ( '<<=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:467:18: '<<='
            {
            match("<<="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LEFTSHIFTEQUAL"

    // $ANTLR start "RIGHTSHIFTEQUAL"
    public final void mRIGHTSHIFTEQUAL() throws RecognitionException {
        try {
            int _type = RIGHTSHIFTEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:469:17: ( '>>=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:469:19: '>>='
            {
            match(">>="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "RIGHTSHIFTEQUAL"

    // $ANTLR start "DOUBLESTAREQUAL"
    public final void mDOUBLESTAREQUAL() throws RecognitionException {
        try {
            int _type = DOUBLESTAREQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:471:17: ( '**=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:471:19: '**='
            {
            match("**="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "DOUBLESTAREQUAL"

    // $ANTLR start "DOUBLESLASHEQUAL"
    public final void mDOUBLESLASHEQUAL() throws RecognitionException {
        try {
            int _type = DOUBLESLASHEQUAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:473:18: ( '//=' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:473:20: '//='
            {
            match("//="); 



            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "DOUBLESLASHEQUAL"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:475:5: ( '.' )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:475:7: '.'
            {
            match('.'); 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:478:2: ( '.' DIGITS ( Exponent )? | DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='.') ) {
                alt5=1;
            }
            else if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:478:4: '.' DIGITS ( Exponent )?
                    {
                    match('.'); 

                    mDIGITS(); 


                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:478:15: ( Exponent )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0=='E'||LA1_0=='e') ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:478:16: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:9: DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent )
                    {
                    mDIGITS(); 


                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:16: ( '.' ( DIGITS ( Exponent )? )? | Exponent )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='.') ) {
                        alt4=1;
                    }
                    else if ( (LA4_0=='E'||LA4_0=='e') ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;

                    }
                    switch (alt4) {
                        case 1 :
                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:17: '.' ( DIGITS ( Exponent )? )?
                            {
                            match('.'); 

                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:21: ( DIGITS ( Exponent )? )?
                            int alt3=2;
                            int LA3_0 = input.LA(1);

                            if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                                alt3=1;
                            }
                            switch (alt3) {
                                case 1 :
                                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:22: DIGITS ( Exponent )?
                                    {
                                    mDIGITS(); 


                                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:29: ( Exponent )?
                                    int alt2=2;
                                    int LA2_0 = input.LA(1);

                                    if ( (LA2_0=='E'||LA2_0=='e') ) {
                                        alt2=1;
                                    }
                                    switch (alt2) {
                                        case 1 :
                                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:30: Exponent
                                            {
                                            mExponent(); 


                                            }
                                            break;

                                    }


                                    }
                                    break;

                            }


                            }
                            break;
                        case 2 :
                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:479:45: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    }
                    break;

            }
            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "LONGINT"
    public final void mLONGINT() throws RecognitionException {
        try {
            int _type = LONGINT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:483:5: ( INT ( 'l' | 'L' ) )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:483:9: INT ( 'l' | 'L' )
            {
            mINT(); 


            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LONGINT"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:489:2: ( ( 'e' | 'E' ) ( '+' | '-' )? DIGITS )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:489:4: ( 'e' | 'E' ) ( '+' | '-' )? DIGITS
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:489:16: ( '+' | '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='+'||LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            mDIGITS(); 


            }


        }
        finally {
        	
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:491:5: ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )? | '0' ( DIGITS )* | '1' .. '9' ( DIGITS )* )
            int alt11=3;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='0') ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1=='X'||LA11_1=='x') ) {
                    alt11=1;
                }
                else {
                    alt11=2;
                }
            }
            else if ( ((LA11_0 >= '1' && LA11_0 <= '9')) ) {
                alt11=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;

            }
            switch (alt11) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:492:9: '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( 'l' | 'L' )?
                    {
                    match('0'); 

                    if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:492:25: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0 >= '0' && LA7_0 <= '9')||(LA7_0 >= 'A' && LA7_0 <= 'F')||(LA7_0 >= 'a' && LA7_0 <= 'f')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:493:9: ( 'l' | 'L' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='L'||LA8_0=='l') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:495:9: '0' ( DIGITS )*
                    {
                    match('0'); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:495:13: ( DIGITS )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:495:13: DIGITS
                    	    {
                    	    mDIGITS(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }
                    break;
                case 3 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:496:9: '1' .. '9' ( DIGITS )*
                    {
                    matchRange('1','9'); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:496:18: ( DIGITS )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0 >= '0' && LA10_0 <= '9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:496:18: DIGITS
                    	    {
                    	    mDIGITS(); 


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }
            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "COMPLEX"
    public final void mCOMPLEX() throws RecognitionException {
        try {
            int _type = COMPLEX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:500:5: ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) )
            int alt12=2;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:500:9: INT ( 'j' | 'J' )
                    {
                    mINT(); 


                    if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:501:9: FLOAT ( 'j' | 'J' )
                    {
                    mFLOAT(); 


                    if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "COMPLEX"

    // $ANTLR start "DIGITS"
    public final void mDIGITS() throws RecognitionException {
        try {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:506:8: ( ( '0' .. '9' )+ )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:506:10: ( '0' .. '9' )+
            {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:506:10: ( '0' .. '9' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }


        }
        finally {
        	
        }
    }
    // $ANTLR end "DIGITS"

    // $ANTLR start "NAME"
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:507:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:507:7: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:508:9: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= '0' && LA14_0 <= '9')||(LA14_0 >= 'A' && LA14_0 <= 'Z')||LA14_0=='_'||(LA14_0 >= 'a' && LA14_0 <= 'z')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "NAME"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:5: ( ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' ) )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:9: ( 'r' | 'u' | 'ur' )? ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:9: ( 'r' | 'u' | 'ur' )?
            int alt15=4;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='r') ) {
                alt15=1;
            }
            else if ( (LA15_0=='u') ) {
                int LA15_2 = input.LA(2);

                if ( (LA15_2=='r') ) {
                    alt15=3;
                }
                else if ( (LA15_2=='\"'||LA15_2=='\'') ) {
                    alt15=2;
                }
            }
            switch (alt15) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:10: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:14: 'u'
                    {
                    match('u'); 

                    }
                    break;
                case 3 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:518:18: 'ur'
                    {
                    match("ur"); 



                    }
                    break;

            }


            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:519:9: ( '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
            int alt20=4;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\'') ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1=='\'') ) {
                    int LA20_3 = input.LA(3);

                    if ( (LA20_3=='\'') ) {
                        alt20=1;
                    }
                    else {
                        alt20=4;
                    }
                }
                else if ( ((LA20_1 >= '\u0000' && LA20_1 <= '\t')||(LA20_1 >= '\u000B' && LA20_1 <= '&')||(LA20_1 >= '(' && LA20_1 <= '\uFFFF')) ) {
                    alt20=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA20_0=='\"') ) {
                int LA20_2 = input.LA(2);

                if ( (LA20_2=='\"') ) {
                    int LA20_5 = input.LA(3);

                    if ( (LA20_5=='\"') ) {
                        alt20=2;
                    }
                    else {
                        alt20=3;
                    }
                }
                else if ( ((LA20_2 >= '\u0000' && LA20_2 <= '\t')||(LA20_2 >= '\u000B' && LA20_2 <= '!')||(LA20_2 >= '#' && LA20_2 <= '\uFFFF')) ) {
                    alt20=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }
            switch (alt20) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:519:13: '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\''
                    {
                    match("'''"); 



                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:519:22: ( options {greedy=false; } : . )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0=='\'') ) {
                            int LA16_1 = input.LA(2);

                            if ( (LA16_1=='\'') ) {
                                int LA16_3 = input.LA(3);

                                if ( (LA16_3=='\'') ) {
                                    alt16=2;
                                }
                                else if ( ((LA16_3 >= '\u0000' && LA16_3 <= '&')||(LA16_3 >= '(' && LA16_3 <= '\uFFFF')) ) {
                                    alt16=1;
                                }


                            }
                            else if ( ((LA16_1 >= '\u0000' && LA16_1 <= '&')||(LA16_1 >= '(' && LA16_1 <= '\uFFFF')) ) {
                                alt16=1;
                            }


                        }
                        else if ( ((LA16_0 >= '\u0000' && LA16_0 <= '&')||(LA16_0 >= '(' && LA16_0 <= '\uFFFF')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:519:47: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    match("'''"); 



                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:520:13: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 



                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:520:19: ( options {greedy=false; } : . )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0=='\"') ) {
                            int LA17_1 = input.LA(2);

                            if ( (LA17_1=='\"') ) {
                                int LA17_3 = input.LA(3);

                                if ( (LA17_3=='\"') ) {
                                    alt17=2;
                                }
                                else if ( ((LA17_3 >= '\u0000' && LA17_3 <= '!')||(LA17_3 >= '#' && LA17_3 <= '\uFFFF')) ) {
                                    alt17=1;
                                }


                            }
                            else if ( ((LA17_1 >= '\u0000' && LA17_1 <= '!')||(LA17_1 >= '#' && LA17_1 <= '\uFFFF')) ) {
                                alt17=1;
                            }


                        }
                        else if ( ((LA17_0 >= '\u0000' && LA17_0 <= '!')||(LA17_0 >= '#' && LA17_0 <= '\uFFFF')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:520:44: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    match("\"\"\""); 



                    }
                    break;
                case 3 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:521:13: '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"'
                    {
                    match('\"'); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:521:17: ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )*
                    loop18:
                    do {
                        int alt18=3;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='\\') ) {
                            alt18=1;
                        }
                        else if ( ((LA18_0 >= '\u0000' && LA18_0 <= '\t')||(LA18_0 >= '\u000B' && LA18_0 <= '!')||(LA18_0 >= '#' && LA18_0 <= '[')||(LA18_0 >= ']' && LA18_0 <= '\uFFFF')) ) {
                            alt18=2;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:521:18: ESC
                    	    {
                    	    mESC(); 


                    	    }
                    	    break;
                    	case 2 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:521:22: ~ ( '\\\\' | '\\n' | '\"' )
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    match('\"'); 

                    }
                    break;
                case 4 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:522:13: '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\''
                    {
                    match('\''); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:522:18: ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )*
                    loop19:
                    do {
                        int alt19=3;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0=='\\') ) {
                            alt19=1;
                        }
                        else if ( ((LA19_0 >= '\u0000' && LA19_0 <= '\t')||(LA19_0 >= '\u000B' && LA19_0 <= '&')||(LA19_0 >= '(' && LA19_0 <= '[')||(LA19_0 >= ']' && LA19_0 <= '\uFFFF')) ) {
                            alt19=2;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:522:19: ESC
                    	    {
                    	    mESC(); 


                    	    }
                    	    break;
                    	case 2 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:522:23: ~ ( '\\\\' | '\\n' | '\\'' )
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    match('\''); 

                    }
                    break;

            }


            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:526:2: ( '\\\\' . )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:526:4: '\\\\' .
            {
            match('\\'); 

            matchAny(); 

            }


        }
        finally {
        	
        }
    }
    // $ANTLR end "ESC"

    // $ANTLR start "CONTINUED_LINE"
    public final void mCONTINUED_LINE() throws RecognitionException {
        try {
            int _type = CONTINUED_LINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:531:2: ( '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )* )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:531:4: '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )*
            {
            match('\\'); 

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:531:9: ( '\\r' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\r') ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:531:10: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:531:22: ( ' ' | '\\t' )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\t'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


             _channel=HIDDEN; 

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "CONTINUED_LINE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:5: ( ( ( '\\r' )? '\\n' )+ )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:9: ( ( '\\r' )? '\\n' )+
            {
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:9: ( ( '\\r' )? '\\n' )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='\n'||LA24_0=='\r') ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:10: ( '\\r' )? '\\n'
            	    {
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:10: ( '\\r' )?
            	    int alt23=2;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0=='\r') ) {
            	        alt23=1;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:542:11: '\\r'
            	            {
            	            match('\r'); 

            	            }
            	            break;

            	    }


            	    match('\n'); 

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);


            if ( startPos==0 || implicitLineJoiningLevel>0 )
                        _channel=HIDDEN;
                    

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:544:4: ({...}? => ( ' ' | '\\t' )+ )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:544:6: {...}? => ( ' ' | '\\t' )+
            {
            if ( !((startPos>0)) ) {
                throw new FailedPredicateException(input, "WS", "startPos>0");
            }

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:544:22: ( ' ' | '\\t' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='\t'||LA25_0==' ') ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            _channel=HIDDEN;

            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "LEADING_WS"
    public final void mLEADING_WS() throws RecognitionException {
        try {
            int _type = LEADING_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;

                int spaces = 0;

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:563:5: ({...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* ) )
            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:563:9: {...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            {
            if ( !((startPos==0)) ) {
                throw new FailedPredicateException(input, "LEADING_WS", "startPos==0");
            }

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:564:6: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==' ') ) {
                int LA30_1 = input.LA(2);

                if ( ((implicitLineJoiningLevel>0)) ) {
                    alt30=1;
                }
                else if ( (true) ) {
                    alt30=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA30_0=='\t') ) {
                int LA30_2 = input.LA(2);

                if ( ((implicitLineJoiningLevel>0)) ) {
                    alt30=1;
                }
                else if ( (true) ) {
                    alt30=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:564:10: {...}? ( ' ' | '\\t' )+
                    {
                    if ( !((implicitLineJoiningLevel>0)) ) {
                        throw new FailedPredicateException(input, "LEADING_WS", "implicitLineJoiningLevel>0");
                    }

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:564:40: ( ' ' | '\\t' )+
                    int cnt26=0;
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0=='\t'||LA26_0==' ') ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    	    {
                    	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt26 >= 1 ) break loop26;
                                EarlyExitException eee =
                                    new EarlyExitException(26, input);
                                throw eee;
                        }
                        cnt26++;
                    } while (true);


                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:565:11: ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )*
                    {
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:565:11: ( ' ' | '\\t' )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=3;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==' ') ) {
                            alt27=1;
                        }
                        else if ( (LA27_0=='\t') ) {
                            alt27=2;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:565:14: ' '
                    	    {
                    	    match(' '); 

                    	     spaces++; 

                    	    }
                    	    break;
                    	case 2 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:566:12: '\\t'
                    	    {
                    	    match('\t'); 

                    	     spaces += 8; spaces -= (spaces % 8); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);



                                // make a string of n spaces where n is column number - 1
                                char[] indentation = new char[spaces];
                                for (int i=0; i<spaces; i++) {
                                    indentation[i] = ' ';
                                }
                                String s = new String(indentation);
                                emit(new ClassicToken(LEADING_WS,new String(indentation)));
                            	

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:578:10: ( ( '\\r' )? '\\n' )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0=='\n'||LA29_0=='\r') ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:578:12: ( '\\r' )? '\\n'
                    	    {
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:578:12: ( '\\r' )?
                    	    int alt28=2;
                    	    int LA28_0 = input.LA(1);

                    	    if ( (LA28_0=='\r') ) {
                    	        alt28=1;
                    	    }
                    	    switch (alt28) {
                    	        case 1 :
                    	            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:578:13: '\\r'
                    	            {
                    	            match('\r'); 

                    	            }
                    	            break;

                    	    }


                    	    match('\n'); 

                    	    if (token!=null) token.setChannel(HIDDEN); else _channel=HIDDEN;

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "LEADING_WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;

                _channel=HIDDEN;

            // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:5: ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ |{...}? => '#' (~ '\\n' )* )
            int alt35=2;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:7: {...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+
                    {
                    if ( !((startPos==0)) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos==0");
                    }

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:24: ( ' ' | '\\t' )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0=='\t'||LA31_0==' ') ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    	    {
                    	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);


                    match('#'); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:40: (~ '\\n' )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( ((LA32_0 >= '\u0000' && LA32_0 <= '\t')||(LA32_0 >= '\u000B' && LA32_0 <= '\uFFFF')) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);


                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:49: ( '\\n' )+
                    int cnt33=0;
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0=='\n') ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:615:49: '\\n'
                    	    {
                    	    match('\n'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt33 >= 1 ) break loop33;
                                EarlyExitException eee =
                                    new EarlyExitException(33, input);
                                throw eee;
                        }
                        cnt33++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:616:7: {...}? => '#' (~ '\\n' )*
                    {
                    if ( !((startPos>0)) ) {
                        throw new FailedPredicateException(input, "COMMENT", "startPos>0");
                    }

                    match('#'); 

                    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:616:27: (~ '\\n' )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( ((LA34_0 >= '\u0000' && LA34_0 <= '\t')||(LA34_0 >= '\u000B' && LA34_0 <= '\uFFFF')) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    }
                    break;

            }
            type = _type;
            channel = _channel;
        }
        finally {
        	
        }
    }
    // $ANTLR end "COMMENT"

    public void mTokens() throws RecognitionException {
        // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:8: ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | NEWLINE | WS | LEADING_WS | COMMENT )
        int alt36=84;
        alt36 = dfa36.predict(input);
        switch (alt36) {
            case 1 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:10: T__64
                {
                mT__64(); 


                }
                break;
            case 2 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:16: T__65
                {
                mT__65(); 


                }
                break;
            case 3 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:22: T__66
                {
                mT__66(); 


                }
                break;
            case 4 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:28: T__67
                {
                mT__67(); 


                }
                break;
            case 5 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:34: T__68
                {
                mT__68(); 


                }
                break;
            case 6 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:40: T__69
                {
                mT__69(); 


                }
                break;
            case 7 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:46: T__70
                {
                mT__70(); 


                }
                break;
            case 8 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:52: T__71
                {
                mT__71(); 


                }
                break;
            case 9 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:58: T__72
                {
                mT__72(); 


                }
                break;
            case 10 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:64: T__73
                {
                mT__73(); 


                }
                break;
            case 11 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:70: T__74
                {
                mT__74(); 


                }
                break;
            case 12 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:76: T__75
                {
                mT__75(); 


                }
                break;
            case 13 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:82: T__76
                {
                mFOR(); 


                }
                break;
            case 14 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:88: T__77
                {
                mT__77(); 


                }
                break;
            case 15 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:94: T__78
                {
                mT__78(); 


                }
                break;
            case 16 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:100: T__79
                {
                mIF(); 


                }
                break;
            case 17 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:106: T__80
                {
                mT__80(); 


                }
                break;
            case 18 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:112: T__81
                {
                mT__81(); 


                }
                break;
            case 19 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:118: T__82
                {
                mT__82(); 


                }
                break;
            case 20 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:124: T__83
                {
                mT__83(); 


                }
                break;
            case 21 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:130: T__84
                {
                mNEGATION_OPERATOR(); 


                }
                break;
            case 22 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:136: T__85
                {
                mT__85(); 


                }
                break;
            case 23 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:142: T__86
                {
                mT__86(); 


                }
                break;
            case 24 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:148: T__87
                {
                mT__87(); 


                }
                break;
            case 25 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:154: T__88
                {
                mT__88(); 


                }
                break;
            case 26 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:160: T__89
                {
                mT__89(); 


                }
                break;
            case 27 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:166: T__90
                {
                mT__90(); 


                }
                break;
            case 28 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:172: T__91
                {
                mWHILE(); 


                }
                break;
            case 29 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:178: T__92
                {
                mT__92(); 


                }
                break;
            case 30 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:184: LPAREN
                {
                mLPAREN(); 


                }
                break;
            case 31 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:191: RPAREN
                {
                mRPAREN(); 


                }
                break;
            case 32 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:198: LBRACK
                {
                mLBRACK(); 


                }
                break;
            case 33 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:205: RBRACK
                {
                mRBRACK(); 


                }
                break;
            case 34 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:212: COLON
                {
                mCOLON(); 


                }
                break;
            case 35 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:218: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 36 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:224: SEMI
                {
                mSEMI(); 


                }
                break;
            case 37 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:229: PLUS
                {
                mPLUS(); 


                }
                break;
            case 38 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:234: MINUS
                {
                mMINUS(); 


                }
                break;
            case 39 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:240: STAR
                {
                mSTAR(); 


                }
                break;
            case 40 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:245: SLASH
                {
                mSLASH(); 


                }
                break;
            case 41 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:251: VBAR
                {
                mVBAR(); 


                }
                break;
            case 42 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:256: AMPER
                {
                mAMPER(); 


                }
                break;
            case 43 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:262: LESS
                {
                mLOWER_THAN_OPERATOR(); 


                }
                break;
            case 44 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:267: GREATER
                {
                mGREATER_THAN_OPERATOR(); 


                }
                break;
            case 45 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:275: ASSIGN
                {
                mASSIGN(); 


                }
                break;
            case 46 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:282: PERCENT
                {
                mPERCENT(); 


                }
                break;
            case 47 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:290: BACKQUOTE
                {
                mBACKQUOTE(); 


                }
                break;
            case 48 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:300: LCURLY
                {
                mLCURLY(); 


                }
                break;
            case 49 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:307: RCURLY
                {
                mRCURLY(); 


                }
                break;
            case 50 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:314: CIRCUMFLEX
                {
                mCIRCUMFLEX(); 


                }
                break;
            case 51 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:325: TILDE
                {
                mTILDE(); 


                }
                break;
            case 52 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:331: EQUAL
                {
                mEQUALITY_OPERATOR(); 


                }
                break;
            case 53 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:337: NOTEQUAL
                {
                mINEQUALITY_OPERATOR(); 


                }
                break;
            case 54 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:346: ALT_NOTEQUAL
                {
                mALT_NOTEQUAL(); 


                }
                break;
            case 55 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:359: LESSEQUAL
                {
                mLESS_OR_EQUAL_OPERATOR(); 


                }
                break;
            case 56 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:369: LEFTSHIFT
                {
                mLEFTSHIFT(); 


                }
                break;
            case 57 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:379: GREATEREQUAL
                {
                mGREATER_OR_EQUAL_OPERATOR(); 


                }
                break;
            case 58 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:392: RIGHTSHIFT
                {
                mRIGHTSHIFT(); 


                }
                break;
            case 59 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:403: PLUSEQUAL
                {
                mPLUSEQUAL(); 


                }
                break;
            case 60 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:413: MINUSEQUAL
                {
                mMINUSEQUAL(); 


                }
                break;
            case 61 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:424: DOUBLESTAR
                {
                mDOUBLESTAR(); 


                }
                break;
            case 62 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:435: STAREQUAL
                {
                mSTAREQUAL(); 


                }
                break;
            case 63 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:445: DOUBLESLASH
                {
                mDOUBLESLASH(); 


                }
                break;
            case 64 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:457: SLASHEQUAL
                {
                mSLASHEQUAL(); 


                }
                break;
            case 65 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:468: VBAREQUAL
                {
                mVBAREQUAL(); 


                }
                break;
            case 66 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:478: PERCENTEQUAL
                {
                mPERCENTEQUAL(); 


                }
                break;
            case 67 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:491: AMPEREQUAL
                {
                mAMPEREQUAL(); 


                }
                break;
            case 68 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:502: CIRCUMFLEXEQUAL
                {
                mCIRCUMFLEXEQUAL(); 


                }
                break;
            case 69 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:518: LEFTSHIFTEQUAL
                {
                mLEFTSHIFTEQUAL(); 


                }
                break;
            case 70 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:533: RIGHTSHIFTEQUAL
                {
                mRIGHTSHIFTEQUAL(); 


                }
                break;
            case 71 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:549: DOUBLESTAREQUAL
                {
                mDOUBLESTAREQUAL(); 


                }
                break;
            case 72 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:565: DOUBLESLASHEQUAL
                {
                mDOUBLESLASHEQUAL(); 


                }
                break;
            case 73 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:582: DOT
                {
                mDOT(); 


                }
                break;
            case 74 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:586: FLOAT
                {
                mFLOAT(); 


                }
                break;
            case 75 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:592: LONGINT
                {
                mLONGINT(); 


                }
                break;
            case 76 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:600: INT
                {
                mINT(); 


                }
                break;
            case 77 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:604: COMPLEX
                {
                mCOMPLEX(); 


                }
                break;
            case 78 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:612: NAME
                {
                mNAME(); 


                }
                break;
            case 79 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:617: STRING
                {
                mSTRING(); 


                }
                break;
            case 80 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:624: CONTINUED_LINE
                {
                mCONTINUED_LINE(); 


                }
                break;
            case 81 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:639: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 82 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:647: WS
                {
                mWS(); 


                }
                break;
            case 83 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:650: LEADING_WS
                {
                mLEADING_WS(); 


                }
                break;
            case 84 :
                // D:\\WORK\\TESIS\\ANTLR\\Gramáticas\\Python.g:1:661: COMMENT
                {
                mCOMMENT(); 


                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA12_eotS =
        "\7\uffff";
    static final String DFA12_eofS =
        "\7\uffff";
    static final String DFA12_minS =
        "\3\56\2\uffff\2\56";
    static final String DFA12_maxS =
        "\1\71\1\170\1\152\2\uffff\2\152";
    static final String DFA12_acceptS =
        "\3\uffff\1\2\1\1\2\uffff";
    static final String DFA12_specialS =
        "\7\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\3\1\uffff\1\1\11\2",
            "\1\3\1\uffff\12\5\13\uffff\1\3\4\uffff\1\4\15\uffff\1\4\14"+
            "\uffff\1\3\4\uffff\1\4\15\uffff\1\4",
            "\1\3\1\uffff\12\6\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4",
            "",
            "",
            "\1\3\1\uffff\12\5\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4",
            "\1\3\1\uffff\12\6\13\uffff\1\3\4\uffff\1\4\32\uffff\1\3\4\uffff"+
            "\1\4"
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
        for (int i=0; i<numStates; i++) {
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
            return "499:1: COMPLEX : ( INT ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) );";
        }
    }
    static final String DFA35_eotS =
        "\2\uffff\2\4\1\uffff";
    static final String DFA35_eofS =
        "\5\uffff";
    static final String DFA35_minS =
        "\1\11\1\uffff\2\0\1\uffff";
    static final String DFA35_maxS =
        "\1\43\1\uffff\2\uffff\1\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\2\uffff\1\2";
    static final String DFA35_specialS =
        "\1\2\1\uffff\1\0\1\1\1\uffff}>";
    static final String[] DFA35_transitionS = {
            "\1\1\26\uffff\1\1\2\uffff\1\2",
            "",
            "\12\3\1\1\ufff5\3",
            "\12\3\1\1\ufff5\3",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "611:1: COMMENT : ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ |{...}? => '#' (~ '\\n' )* );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_2 = input.LA(1);

                         
                        int index35_2 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((LA35_2 >= '\u0000' && LA35_2 <= '\t')||(LA35_2 >= '\u000B' && LA35_2 <= '\uFFFF')) && (((startPos==0)||(startPos>0)))) {s = 3;}

                        else if ( (LA35_2=='\n') && ((startPos==0))) {s = 1;}

                        else s = 4;

                         
                        input.seek(index35_2);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA35_3 = input.LA(1);

                         
                        int index35_3 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA35_3=='\n') && ((startPos==0))) {s = 1;}

                        else if ( ((LA35_3 >= '\u0000' && LA35_3 <= '\t')||(LA35_3 >= '\u000B' && LA35_3 <= '\uFFFF')) && (((startPos==0)||(startPos>0)))) {s = 3;}

                        else s = 4;

                         
                        input.seek(index35_3);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA35_0 = input.LA(1);

                         
                        int index35_0 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA35_0=='\t'||LA35_0==' ') && ((startPos==0))) {s = 1;}

                        else if ( (LA35_0=='#') && (((startPos==0)||(startPos>0)))) {s = 2;}

                         
                        input.seek(index35_0);

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA36_eotS =
        "\1\uffff\20\54\7\uffff\1\116\1\120\1\123\1\126\1\130\1\132\1\136"+
        "\1\141\1\143\1\145\3\uffff\1\147\2\uffff\1\150\2\153\1\54\4\uffff"+
        "\2\163\1\uffff\14\54\1\u0085\1\54\1\u0087\1\u0088\2\54\1\u008b\7"+
        "\54\4\uffff\1\u0094\2\uffff\1\u0096\10\uffff\1\u0098\2\uffff\1\u009a"+
        "\10\uffff\1\u009b\2\uffff\1\u009b\1\uffff\1\153\2\uffff\1\153\1"+
        "\54\3\uffff\1\u00a2\4\54\1\u00a7\1\u00a8\5\54\1\u00ae\2\54\1\uffff"+
        "\1\54\2\uffff\1\54\1\u00b3\1\uffff\4\54\1\u00b8\2\54\12\uffff\1"+
        "\153\1\u009b\1\uffff\1\u009b\2\uffff\4\54\2\uffff\1\u00c3\1\u00c4"+
        "\1\54\1\u00c6\1\54\1\uffff\1\u00c8\3\54\1\uffff\1\u00cc\3\54\1\uffff"+
        "\2\54\1\uffff\1\u009b\1\157\1\uffff\1\54\1\u00d5\1\u00d6\1\54\2"+
        "\uffff\1\54\1\uffff\1\54\1\uffff\3\54\1\uffff\1\u00dd\1\u00de\1"+
        "\54\1\u00e0\1\u00e1\1\uffff\1\u009b\1\u00e2\2\uffff\1\54\1\u00e4"+
        "\1\54\1\u00e6\1\u00e7\1\u00e8\2\uffff\1\u00e9\3\uffff\1\54\1\uffff"+
        "\1\u00eb\4\uffff\1\u00ec\2\uffff";
    static final String DFA36_eofS =
        "\u00ed\uffff";
    static final String DFA36_minS =
        "\1\11\1\156\1\162\1\154\1\145\1\154\1\151\1\154\1\146\1\141\1\157"+
        "\1\162\1\141\1\42\1\162\1\150\1\151\7\uffff\2\75\1\52\1\57\2\75"+
        "\1\74\3\75\3\uffff\1\75\2\uffff\1\60\2\56\1\42\4\uffff\2\11\1\uffff"+
        "\1\144\1\163\1\145\1\141\1\156\1\146\1\151\1\143\1\156\1\162\2\157"+
        "\1\60\1\160\2\60\1\155\1\164\1\60\1\163\2\151\1\164\1\171\1\151"+
        "\1\145\4\uffff\1\75\2\uffff\1\75\10\uffff\1\75\2\uffff\1\75\10\uffff"+
        "\2\60\1\uffff\1\60\1\53\1\56\2\uffff\1\56\1\42\1\0\2\uffff\1\60"+
        "\1\145\1\141\1\163\1\164\2\60\1\146\2\145\1\143\1\141\1\60\1\155"+
        "\1\142\1\uffff\1\157\2\uffff\1\142\1\60\1\uffff\1\163\1\156\1\163"+
        "\1\165\1\60\2\154\11\uffff\1\53\4\60\2\uffff\1\162\1\153\1\163\1"+
        "\151\2\uffff\2\60\1\160\1\60\1\154\1\uffff\1\60\1\141\1\162\1\144"+
        "\1\uffff\1\60\1\164\1\145\1\162\1\uffff\1\145\1\144\2\60\1\112\1"+
        "\53\1\164\2\60\1\156\2\uffff\1\164\1\uffff\1\154\1\uffff\1\154\1"+
        "\164\1\141\1\uffff\2\60\1\156\5\60\2\uffff\1\165\1\60\1\171\3\60"+
        "\2\uffff\1\60\3\uffff\1\145\1\uffff\1\60\4\uffff\1\60\2\uffff";
    static final String DFA36_maxS =
        "\1\176\1\163\1\162\1\157\1\145\1\170\1\162\1\154\1\163\1\141\1\157"+
        "\2\162\1\145\1\162\1\150\1\151\7\uffff\6\75\2\76\2\75\3\uffff\1"+
        "\75\2\uffff\1\71\1\170\1\154\1\162\4\uffff\2\43\1\uffff\1\144\1"+
        "\163\1\145\1\141\1\156\1\154\1\163\1\145\1\156\1\162\2\157\1\172"+
        "\1\160\2\172\1\155\1\164\1\172\1\163\2\151\1\164\1\171\1\151\1\145"+
        "\4\uffff\1\75\2\uffff\1\75\10\uffff\1\75\2\uffff\1\75\10\uffff\1"+
        "\152\1\146\1\uffff\1\152\1\71\1\154\2\uffff\1\154\1\47\1\0\2\uffff"+
        "\1\172\1\145\1\141\1\163\1\164\2\172\1\146\2\145\1\143\1\141\1\172"+
        "\1\155\1\142\1\uffff\1\157\2\uffff\1\142\1\172\1\uffff\1\163\1\156"+
        "\1\163\1\165\1\172\2\154\11\uffff\1\71\1\154\1\152\1\71\1\152\2"+
        "\uffff\1\162\1\153\1\163\1\151\2\uffff\2\172\1\160\1\172\1\154\1"+
        "\uffff\1\172\1\141\1\162\1\144\1\uffff\1\172\1\164\1\145\1\162\1"+
        "\uffff\1\145\1\144\1\71\2\152\1\71\1\164\2\172\1\156\2\uffff\1\164"+
        "\1\uffff\1\154\1\uffff\1\154\1\164\1\141\1\uffff\2\172\1\156\2\172"+
        "\1\71\1\152\1\172\2\uffff\1\165\1\172\1\171\3\172\2\uffff\1\172"+
        "\3\uffff\1\145\1\uffff\1\172\4\uffff\1\172\2\uffff";
    static final String DFA36_acceptS =
        "\21\uffff\1\36\1\37\1\40\1\41\1\42\1\43\1\44\12\uffff\1\57\1\60"+
        "\1\61\1\uffff\1\63\1\65\4\uffff\1\116\1\117\1\120\1\121\2\uffff"+
        "\1\124\32\uffff\1\73\1\45\1\74\1\46\1\uffff\1\76\1\47\1\uffff\1"+
        "\100\1\50\1\101\1\51\1\103\1\52\1\66\1\67\1\uffff\1\53\1\71\1\uffff"+
        "\1\54\1\64\1\55\1\102\1\56\1\104\1\62\1\111\2\uffff\1\114\3\uffff"+
        "\1\113\1\115\3\uffff\1\123\1\124\17\uffff\1\20\1\uffff\1\22\1\23"+
        "\2\uffff\1\26\7\uffff\1\107\1\75\1\110\1\77\1\105\1\70\1\106\1\72"+
        "\1\112\5\uffff\1\122\1\1\4\uffff\1\6\1\7\5\uffff\1\15\4\uffff\1"+
        "\25\4\uffff\1\33\12\uffff\1\10\1\11\1\uffff\1\13\1\uffff\1\16\3"+
        "\uffff\1\27\10\uffff\1\3\1\4\6\uffff\1\30\1\31\1\uffff\1\34\1\35"+
        "\1\2\1\uffff\1\12\1\uffff\1\17\1\21\1\24\1\32\1\uffff\1\14\1\5";
    static final String DFA36_specialS =
        "\1\3\57\uffff\1\2\1\1\101\uffff\1\0\171\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\61\1\57\2\uffff\1\57\22\uffff\1\60\1\47\1\55\1\62\1\uffff"+
            "\1\41\1\35\1\55\1\21\1\22\1\32\1\30\1\26\1\31\1\50\1\33\1\51"+
            "\11\52\1\25\1\27\1\36\1\40\1\37\2\uffff\32\54\1\23\1\56\1\24"+
            "\1\45\1\54\1\42\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\54\1\10\2\54\1"+
            "\11\1\54\1\12\1\13\1\14\1\54\1\15\1\54\1\16\1\53\1\54\1\17\1"+
            "\54\1\20\1\54\1\43\1\34\1\44\1\46",
            "\1\63\4\uffff\1\64",
            "\1\65",
            "\1\66\2\uffff\1\67",
            "\1\70",
            "\1\71\13\uffff\1\72",
            "\1\73\5\uffff\1\74\2\uffff\1\75",
            "\1\76",
            "\1\77\6\uffff\1\100\1\101\4\uffff\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106\20\uffff\1\107",
            "\1\55\4\uffff\1\55\71\uffff\1\110\3\uffff\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\115",
            "\1\117",
            "\1\121\22\uffff\1\122",
            "\1\124\15\uffff\1\125",
            "\1\127",
            "\1\131",
            "\1\135\1\134\1\133",
            "\1\137\1\140",
            "\1\142",
            "\1\144",
            "",
            "",
            "",
            "\1\146",
            "",
            "",
            "\12\151",
            "\1\154\1\uffff\12\156\13\uffff\1\155\4\uffff\1\160\1\uffff"+
            "\1\157\13\uffff\1\152\14\uffff\1\155\4\uffff\1\160\1\uffff\1"+
            "\157\13\uffff\1\152",
            "\1\154\1\uffff\12\161\13\uffff\1\155\4\uffff\1\160\1\uffff"+
            "\1\157\30\uffff\1\155\4\uffff\1\160\1\uffff\1\157",
            "\1\55\4\uffff\1\55\112\uffff\1\162",
            "",
            "",
            "",
            "",
            "\1\61\1\164\2\uffff\1\164\22\uffff\1\60\2\uffff\1\165",
            "\1\61\1\164\2\uffff\1\164\22\uffff\1\60\2\uffff\1\165",
            "",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173\5\uffff\1\174",
            "\1\175\11\uffff\1\176",
            "\1\177\1\uffff\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0086",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u0089",
            "\1\u008a",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "",
            "",
            "",
            "",
            "\1\u0093",
            "",
            "",
            "\1\u0095",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0097",
            "",
            "",
            "\1\u0099",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\151\13\uffff\1\u009c\4\uffff\1\160\32\uffff\1\u009c\4\uffff"+
            "\1\160",
            "\12\u009d\7\uffff\6\u009d\32\uffff\6\u009d",
            "",
            "\12\u009e\20\uffff\1\160\37\uffff\1\160",
            "\1\u009f\1\uffff\1\u009f\2\uffff\12\u00a0",
            "\1\154\1\uffff\12\156\13\uffff\1\155\4\uffff\1\160\1\uffff"+
            "\1\157\30\uffff\1\155\4\uffff\1\160\1\uffff\1\157",
            "",
            "",
            "\1\154\1\uffff\12\161\13\uffff\1\155\4\uffff\1\160\1\uffff"+
            "\1\157\30\uffff\1\155\4\uffff\1\160\1\uffff\1\157",
            "\1\55\4\uffff\1\55",
            "\1\uffff",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00af",
            "\1\u00b0",
            "",
            "\1\u00b1",
            "",
            "",
            "\1\u00b2",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00b9",
            "\1\u00ba",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u00bb\1\uffff\1\u00bb\2\uffff\12\u00bc",
            "\12\u009d\7\uffff\6\u009d\3\uffff\1\160\1\uffff\1\u00bd\24"+
            "\uffff\6\u009d\3\uffff\1\160\1\uffff\1\u00bd",
            "\12\u009e\13\uffff\1\u00be\4\uffff\1\160\32\uffff\1\u00be\4"+
            "\uffff\1\160",
            "\12\u00a0",
            "\12\u00a0\20\uffff\1\160\37\uffff\1\160",
            "",
            "",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c5",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c7",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "",
            "\1\u00d0",
            "\1\u00d1",
            "\12\u00bc",
            "\12\u00bc\20\uffff\1\160\37\uffff\1\160",
            "\1\160\37\uffff\1\160",
            "\1\u00d2\1\uffff\1\u00d2\2\uffff\12\u00d3",
            "\1\u00d4",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00d7",
            "",
            "",
            "\1\u00d8",
            "",
            "\1\u00d9",
            "",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00df",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\u00d3",
            "\12\u00d3\20\uffff\1\160\37\uffff\1\160",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\1\u00e3",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\1\u00e5",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "",
            "\1\u00ea",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            "",
            "",
            "",
            "\12\54\7\uffff\32\54\4\uffff\1\54\1\uffff\32\54",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | DOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | NEWLINE | WS | LEADING_WS | COMMENT );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_115 = input.LA(1);

                         
                        int index36_115 = input.index();
                        input.rewind();

                        s = -1;
                        if ( ((startPos>0)) ) {s = 161;}

                        else if ( ((startPos==0)) ) {s = 116;}

                         
                        input.seek(index36_115);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA36_49 = input.LA(1);

                         
                        int index36_49 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA36_49==' ') && (((startPos==0)||(startPos>0)))) {s = 48;}

                        else if ( (LA36_49=='\n'||LA36_49=='\r') && ((startPos==0))) {s = 116;}

                        else if ( (LA36_49=='\t') && (((startPos==0)||(startPos>0)))) {s = 49;}

                        else if ( (LA36_49=='#') && ((startPos==0))) {s = 117;}

                        else s = 115;

                         
                        input.seek(index36_49);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA36_48 = input.LA(1);

                         
                        int index36_48 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA36_48==' ') && (((startPos==0)||(startPos>0)))) {s = 48;}

                        else if ( (LA36_48=='\n'||LA36_48=='\r') && ((startPos==0))) {s = 116;}

                        else if ( (LA36_48=='\t') && (((startPos==0)||(startPos>0)))) {s = 49;}

                        else if ( (LA36_48=='#') && ((startPos==0))) {s = 117;}

                        else s = 115;

                         
                        input.seek(index36_48);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA36_0 = input.LA(1);

                         
                        int index36_0 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (LA36_0=='a') ) {s = 1;}

                        else if ( (LA36_0=='b') ) {s = 2;}

                        else if ( (LA36_0=='c') ) {s = 3;}

                        else if ( (LA36_0=='d') ) {s = 4;}

                        else if ( (LA36_0=='e') ) {s = 5;}

                        else if ( (LA36_0=='f') ) {s = 6;}

                        else if ( (LA36_0=='g') ) {s = 7;}

                        else if ( (LA36_0=='i') ) {s = 8;}

                        else if ( (LA36_0=='l') ) {s = 9;}

                        else if ( (LA36_0=='n') ) {s = 10;}

                        else if ( (LA36_0=='o') ) {s = 11;}

                        else if ( (LA36_0=='p') ) {s = 12;}

                        else if ( (LA36_0=='r') ) {s = 13;}

                        else if ( (LA36_0=='t') ) {s = 14;}

                        else if ( (LA36_0=='w') ) {s = 15;}

                        else if ( (LA36_0=='y') ) {s = 16;}

                        else if ( (LA36_0=='(') ) {s = 17;}

                        else if ( (LA36_0==')') ) {s = 18;}

                        else if ( (LA36_0=='[') ) {s = 19;}

                        else if ( (LA36_0==']') ) {s = 20;}

                        else if ( (LA36_0==':') ) {s = 21;}

                        else if ( (LA36_0==',') ) {s = 22;}

                        else if ( (LA36_0==';') ) {s = 23;}

                        else if ( (LA36_0=='+') ) {s = 24;}

                        else if ( (LA36_0=='-') ) {s = 25;}

                        else if ( (LA36_0=='*') ) {s = 26;}

                        else if ( (LA36_0=='/') ) {s = 27;}

                        else if ( (LA36_0=='|') ) {s = 28;}

                        else if ( (LA36_0=='&') ) {s = 29;}

                        else if ( (LA36_0=='<') ) {s = 30;}

                        else if ( (LA36_0=='>') ) {s = 31;}

                        else if ( (LA36_0=='=') ) {s = 32;}

                        else if ( (LA36_0=='%') ) {s = 33;}

                        else if ( (LA36_0=='`') ) {s = 34;}

                        else if ( (LA36_0=='{') ) {s = 35;}

                        else if ( (LA36_0=='}') ) {s = 36;}

                        else if ( (LA36_0=='^') ) {s = 37;}

                        else if ( (LA36_0=='~') ) {s = 38;}

                        else if ( (LA36_0=='!') ) {s = 39;}

                        else if ( (LA36_0=='.') ) {s = 40;}

                        else if ( (LA36_0=='0') ) {s = 41;}

                        else if ( ((LA36_0 >= '1' && LA36_0 <= '9')) ) {s = 42;}

                        else if ( (LA36_0=='u') ) {s = 43;}

                        else if ( ((LA36_0 >= 'A' && LA36_0 <= 'Z')||LA36_0=='_'||LA36_0=='h'||(LA36_0 >= 'j' && LA36_0 <= 'k')||LA36_0=='m'||LA36_0=='q'||LA36_0=='s'||LA36_0=='v'||LA36_0=='x'||LA36_0=='z') ) {s = 44;}

                        else if ( (LA36_0=='\"'||LA36_0=='\'') ) {s = 45;}

                        else if ( (LA36_0=='\\') ) {s = 46;}

                        else if ( (LA36_0=='\n'||LA36_0=='\r') ) {s = 47;}

                        else if ( (LA36_0==' ') && (((startPos==0)||(startPos>0)))) {s = 48;}

                        else if ( (LA36_0=='\t') && (((startPos==0)||(startPos>0)))) {s = 49;}

                        else if ( (LA36_0=='#') && (((startPos==0)||(startPos>0)))) {s = 50;}

                         
                        input.seek(index36_0);

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

}