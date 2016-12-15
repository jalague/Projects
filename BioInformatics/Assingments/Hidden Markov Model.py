# -*- coding: utf-8 -*-
"""
Created on Wed Nov  2 22:16:24 2016

@author: John
"""

import numpy as np



file="seq.txt"
file2='sample_FASTA_dna.txt'
#data=read


T={ "S1":{'S1': .5, 'S2':.4, 'S3':.1}, "S2": {'S1': 0, 'S2':.5, 'S3':.5}, "S3": {'S1': .3, 'S2':.2, 'S3':.5}}
#log_T={ "S1":{'S1': -2, 'S2': -1.3219, 'S3': -3.3219}, "S2": {'S1': MA, 'S2':.5, 'S3':.5}, "S3": {'S1': .3, 'S2':.2, 'S3':.5}}

e={ "S1":{'a': .4, 'c':.3, 't':.2, 'g': .1}, "S2": {'a': .25, 'c':.25, 't':.25, 'g':.25}, "S3": {'a': .1, 'c':.2, 't':.3, 'g':.4}}
#log_e=
int_prob={'S1': .25, 'S2': .5, 'S3': .25}
#log_int=
m= ["a","c","t","g"]
N=['S1', 'S2', 'S3']


def readSeq(file):
    sequence= open(file, 'r')
    lines= sequence.readlines()
    noNumbers= []    
    for line in lines:
        for letter in line:
            if letter=='A' or letter=='a' or letter=='C' or letter=='c' or letter=='T' or letter=='t' or letter=='G' or letter=='g' :
                noNumbers.append(letter.lower())
   # seq=noNumbers.upper()
    return(noNumbers)

'''
def markov(t, i):
    if(t==1):
        return int_prob[i]*e[i][1]
    else:
        return e[1]
'''    
    
    
    
def HMM_viterbi(obs, states, start_p, trans_p, emit_p):
     V = [{}]
     for st in states:
         V[0][st] = {"prob": start_p[st] * emit_p[st][obs[0]], "prev": None}
     for t in range(1, len(obs)):
         V.append({})
         for st in states:
             max_tr_prob = max(V[t-1][prev_st]["prob"]*trans_p[prev_st][st] for prev_st in states)
             for prev_st in states:
                 if V[t-1][prev_st]["prob"] * trans_p[prev_st][st] == max_tr_prob:
                     max_prob = max_tr_prob * emit_p[st][obs[t]]
                     V[t][st] = {"prob": max_prob, "prev": prev_st}
                     break
    
     trace = []
     max_prob = max(value["prob"] for value in V[-1].values())
     previous = None
     for st, data in V[-1].items():
         if data["prob"] == max_prob:
             trace.append(st)
             previous = st
             break
     for t in range(len(V) - 2, -1, -1):
         trace.insert(0, V[t + 1][previous]["prev"])
         previous = V[t + 1][previous]["prev"]
 
     print ('Steps: ' )
     print(trace)
     print('Best probability: %s' % max_prob)


data=readSeq(file2)
#print(data)
HMM_viterbi(data, N, int_prob, T, e)