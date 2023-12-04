# BiblioLinguist

### Specification:
- BiblioLinguist is a language-learning application designed to help users strengthen their reading comprehension skills in foreign languages
- The program will provide the user with a natively-used text (a news article, a wikipedia article, or a story) along with an AI-generated quiz based on the contents of that text (powered by OpenAI model GPT-3.5)
- The user will be able to submit their answers to the quiz and view their score, as well as their average score among all tests
- The program also consists of a reading difficulty calculation algorithm, which is an adaptation of Flesch-Kincaid Readability test 

### Instructions:
- Obtain a functioning OpenAI API key from https://openai.com/, and a World News API key from https://worldnewsapi.com/
- Within ```src/app/Main.java```, edit the run configuration's environment variables in the following format OPEN_AI_API_TOKEN=<YOUR_API_KEY>;NEWS_API_TOKEN=<YOUR_API_KEY>
- Run ```Main.java``` to start the program
