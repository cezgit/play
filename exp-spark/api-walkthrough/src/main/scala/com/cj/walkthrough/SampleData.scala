package com.cj.walkthrough

trait SampleData {

  val paragraphs = Seq(
    """On the 24th of February, 1815, the look-out at Notre-Dame de la Garde
      |signalled the three-master, the Pharaon from Smyrna, Trieste, and
      |Naples.""".stripMargin,

    """As usual, a pilot put off immediately, and rounding the Château d’If,
      |got on board the vessel between Cape Morgiou and Rion island.""".stripMargin,

    """Immediately, and according to custom, the ramparts of Fort Saint-Jean
      |were covered with spectators; it is always an event at Marseilles for a
      |ship to come into port, especially when this ship, like the Pharaon, has
      |been built, rigged, and laden at the old Phocee docks, and belongs to an
      |owner of the city.""".stripMargin,

    """The ship drew on and had safely passed the strait, which some volcanic
      |shock has made between the Calasareigne and Jaros islands; had doubled
      |Pomègue, and approached the harbor under topsails, jib, and spanker, but
      |so slowly and sedately that the idlers, with that instinct which is the
      |forerunner of evil, asked one another what misfortune could have
      |happened on board. However, those experienced in navigation saw plainly
      |that if any accident had occurred, it was not to the vessel herself, for
      |she bore down with all the evidence of being skilfully handled, the
      |anchor a-cockbill, the jib-boom guys already eased off, and standing by
      |the side of the pilot, who was steering the Pharaon towards the narrow
      |entrance of the inner port, was a young man, who, with activity and
      |vigilant eye, watched every motion of the ship, and repeated each
      |direction of the pilot.""".stripMargin,

    """The vague disquietude which prevailed among the spectators had so much
      |affected one of the crowd that he did not await the arrival of the
      |vessel in harbor, but jumping into a small skiff, desired to be pulled
      |alongside the Pharaon, which he reached as she rounded into La Réserve
      |basin.""".stripMargin,

    """When the young man on board saw this person approach, he left his
      |station by the pilot, and, hat in hand, leaned over the ship’s bulwarks.""".stripMargin,

    """He was a fine, tall, slim young fellow of eighteen or twenty, with black
      |eyes, and hair as dark as a raven’s wing; and his whole appearance
      |bespoke that calmness and resolution peculiar to men accustomed from
      |their cradle to contend with danger.""".stripMargin)

  val stopWords = Set("a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and", "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "i", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re", "same", "said", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the","a", "able", "about", "across", "after", "all", "almost", "also", "am", "among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it", "its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said", "say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what", "when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your")

}
