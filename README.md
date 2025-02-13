# 📚 BiblioLinguist

## Overview
BiblioLinguist is a **Java-based language-learning application** designed to improve reading comprehension in foreign languages. It **dynamically retrieves real-world articles** and generates **AI-powered quizzes** to test user understanding. 

Support for 🇬🇧 English, 🇫🇷 French, 🇪🇸 Spanish, and 🇩🇪 German!


## 🛠️ Key Features

✍️ **AI-Generated Quizzes**
- Uses OpenAI’s GPT-3.5 to generate context-aware questions based on article content.
- Manages API interactions with asynchronous requests to optimize performance.  

⏱️ **Real-Time Content**
- Fetches articles from global news sources via API, ensuring diverse and up-to-date reading material in any desired language.
- Uses HTTP requests and JSON parsing via Java’s `HttpURLConnection`.  

✅ **Readability Assessment** 
- Implements a custom Flesch-Kincaid-inspired algorithm to evaluate and adapt text difficulty.
- Normalizes linguistic complexity scores across multiple readings to improve algorithm accuracy.  

📈 **Performance Tracking** 
- Displays quiz scores and calculates the user's average performance over multiple attempts.
- Maintains scores locally to enables real-time computation of user performance metrics.

💾 **Robust Code Management** 
- Version-controlled with Git and GitHub, ensuring structured commits and maintainability.



## 🛠️ **Tech Stack**  
| **Category**       | **Technology**            |  
|-------------------|------------------------|  
| **Programming Language** | Java  |  
| **AI & NLP** | OpenAI GPT-3.5 |  
| **Data Retrieval** | World News API |  
| **Algorithm** | Custom Readability Algorithm (inspired by Flesch-Kincaid) |  
| **Version Control** | Git, GitHub |  

Implemented according to **Clean Architecture principles** via an object-oriented layered design to ensure modularity, testability, and maintainability.

## 📜 Instructions
- Obtain a functioning OpenAI API key from https://openai.com/, and a World News API key from https://worldnewsapi.com/
- Within ```src/app/Main.java```, edit the run configuration's environment variables in the following format OPEN_AI_API_TOKEN=<YOUR_API_KEY>;NEWS_API_TOKEN=<YOUR_API_KEY>
- Run ```Main.java``` to start the program
