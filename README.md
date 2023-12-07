# BiblioLinguist

### A language-learning application designed to strengthen reading comprehension skills in foreign languages.
Includes support for any learner, from the beginner to the master.

## Specification:
- Provides the user with a natively-used text, such as a news article, Wikipedia article, or story, along with an AI-generated quiz based on its contents.
- Scores are saved in a local database, allowing users to view their past scores, average, and progression.
- Offers a flexible selection of reading difficulties based on a custom reading difficulty calculation algorithm, an adaptation of the Flesch-Kincaid Readibility test.- The program also consists of a reading difficulty calculation algorithm, which is an adaptation of Flesch-Kincaid Readability test

## Instructions:
- Obtain a functioning OpenAI API key from https://openai.com/ and a World News API key from https://worldnewsapi.com/
- Within ```src/app/Main.java```, edit the run configuration's environment variables in the following format: 
OPEN_AI_API_TOKEN=<YOUR_API_KEY>;NEWS_API_TOKEN=<YOUR_API_KEY>
- Run ```Main.java``` to start the program
