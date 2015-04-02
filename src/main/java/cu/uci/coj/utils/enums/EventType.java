/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.utils.enums;

/**
 * IMPORTANTE: El orden de las constantes enumeradas MUST NOT CHANGE!! Los
 * eventos utilizan el ordinal en la base de datos. Para reorganizar el orden de
 * los tipos de eventos, es necesario purgar primero la base de datos de eventos
 * pendientes.
 * 
 * @author jasoria
 *
 */
public enum EventType {

	PROBLEM_FAILED, PROBLEM_SOLVED, 
	REJUDGE_SUBMIT, REJUDGE_SUBMIT_RANGE, REJUDGE_PROBLEM,

	PUBLIC_CONTEST_CREATED,

	USER_LOGGED_IN,

	CONTEST_STARTED,
	CONTEST_ENDED

}
