import re

class Word:
    def __init__(self):
        self.words_frequencies = 0
        self.words = []

    def add_word(self, word):
        self.words.append(word)

    def add_frequencies(self, f):
        self.words_frequencies += f

def parse_string(str):
    pattern = re.compile(r'\(.*?\)')
    find_result = pattern.findall(str)
    result_list = []
    for f in find_result:
        f = f.replace("(", "").replace(")", "").replace("”", "").replace("“", "").split(",")
        result_list.append(f)
    return result_list


print("Input file name:")
file=input()
f=open(file)
words_frequencies = f.readline()
synonyms = f.readline()
synonyms_list = parse_string(synonyms)
word_dict = {}
all_word = []
for s in synonyms_list:
    if s[0] in word_dict and s[1] in word_dict:
        w1 = word_dict[s[0]]
        w2 = word_dict[s[1]]
        if w1 is not w2:
            w1.words.extend(w2.words)
            for w in w2.words:
                word_dict[w] = w1
            all_word.remove(w2)

    for i in range(2):
        if s[i] not in word_dict:
            if s[1 - i] in word_dict:
                word = word_dict[s[1 - i]]
            else:
                word = Word()
                all_word.append(word)
            word.add_word(s[i])
            word_dict[s[i]] = word

words_frequencies_list = parse_string(words_frequencies)
frequencies = {}
for w in words_frequencies_list:
    frequencies[w[0]] = int(w[1])
for f in frequencies:
    word_dict[f].add_frequencies(frequencies[f])
print_result = []

for a in all_word:
    a.words.sort()
    represent_word = a.words[0]
    print_result.append((represent_word, a.words_frequencies))

result = "Output: CF[]= {"
result += "(“" + print_result[0][0] + "”," + str(print_result[0][1]) + ")"
for i in range(1, len(print_result)):
    result += ",(“" + print_result[i][0] + "”," + str(print_result[i][1]) + ")"
result += "} of size " + str(len(print_result))
print(result)
