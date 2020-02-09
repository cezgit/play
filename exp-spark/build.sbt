lazy val sparkTraining = (project in file(".")).aggregate(wordCountMapReduce, apiWalkThrough, logAnalysisExercise, logAnalysisSolution)
lazy val wordCountMapReduce = project in file("wordcount-mr")
lazy val apiWalkThrough = project in file("api-walkthrough")
lazy val logAnalysisExercise = project in file("log-analysis/exercise")
lazy val logAnalysisSolution = project in file("log-analysis/solution")
