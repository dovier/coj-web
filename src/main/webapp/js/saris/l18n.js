/**
   Localisation script for S4RiS StanD.
   Save with codepage UTF-8.
*/

function L18n(){
  
   /**
      JSON Object with all string constants in all languages.
   */
   var globalTranslations = {
      "ru":{
         "solvedProblems": "Задачи",
         "penalty":"Штраф",
         "place":"Место",
         "chooseLogFormat":"Выберите формат лога",
         "chooseFrameSize":"Выберите количество одновременно отображаемых участников",
         "chooseCurrentPos":"Выберите количество отображаемых участников, выше \"размораживаемого\"",
         "insertLog":"Вставьте лог соревнования",
         "load":"Загрузить",
         "loadError":"Ошибка парсинга лога. Проверьте содержимое лога и выбранный формат",
         "wrongInputCombination":"Нельзя отобразить таблицу с таким количеством строк и количеством строк, выше \"размораживаемой\""
      },
      "en":{
         "solvedProblems": "Problems",
         "penalty":"Penalty",
         "place":"Place",
         "chooseLogFormat":"Choose log format",
         "chooseFrameSize":"Select the number of participants simultaneously displayed",
         "chooseCurrentPos":"Select the number of participants displayed above \"unfreezes\"",
         "insertLog":"Insert contest log",
         "load":"Load",
         "loadError":"Parse log error. Check the contents of the log and the chosen format",
         "wrongInputCombination":"Can not display a table of the number of rows and number of lines, up \"unfreezes\""
      }
   }

   /**
      Return browser localse - "ru","en" etc.
      "en" by default.
   */
   this.getBrowserLang = function() {
      var lang = (navigator.language ||navigator.systemLanguage || navigator.userLanguage || 'en').substr(0, 2).toLowerCase();
      return lang;
   }
   
   /**
      Return translation in browser language.
   */
   this.autoTranslate = function(phrase){
      return this.translate(this.getBrowserLang(),phrase);
   }
   
   /**
      Return translation of prase in language lang.
   */
   this.translate = function(lang,phrase){
      var language = globalTranslations[lang]
      if (language == undefined || language == null){
         if (lang != "en" ){
            return this.translate("en",phrase);
         }else{
            return "English translation not found";
         }
      }
      var translation = language[phrase];
      if (translation == undefined  || translation == null){
         return "No translation for phrase="+phrase+" in language="+lang;
      }
      return translation;
   }
}