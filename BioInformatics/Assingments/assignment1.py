# -*- coding: utf-8 -*-
"""
Created on Thu Aug 25 11:46:10 2016

@author: John

mRNA Translator, into 6 frames

Read in file of sequence
translate into three letter codons
parse for start and stop codons and mark them for position 1 2 and 3
find reverse compliment of sequence and repeat 

"""

import sys

def readSeq(file):
    sequence= open(file, 'r')
    lines= sequence.readlines()
    noNumbers= ''    
    for line in lines:
        for letter in line:
            if letter=='A' or letter=='a' or letter=='C' or letter=='c' or letter=='T' or letter=='t' or letter=='G' or letter=='g' :
                noNumbers+=letter.capitalize()
   # seq=noNumbers.upper()
    return(noNumbers)
 
def translate1(sequence):
    translation=''
    acids ={'TTT': 'F', 'TTC': 'F', 'TTA': 'L', 'TTG': 'L', 'CTT': 'L', 'CTC': 'L', 'CTA':'L','CTG':'L',
    'ATT': 'I','ATC':'I','ATA':'I', 'ATG':'M', 'GTT': 'V', 'GTC': 'V', 'GTA':'V', 'GTG':'V','TCT':'S',
    'TCC':'S','TCA':'S','TCG':'S','CCT':'P', 'CCC':'P','CCA':'P', 'CCG':'P', 'ACT':'T','ACC':'T', 'ACA':'T','ACG':'T','GCT':'A', 'GCC':'A','GCA':'A',
    'GCG':'A','TAT':'Y', 'TAC':'Y', 'TAA': 'Stop', 'TAG':'Stop', 'CAT':'H','CAC':'H','CAA':'Q', 'CAG':'Q',
    'AAT':'N', 'AAC':'N', 'AAA':'K','AAG':'K', 'GAT':'D','GAC':'D', 'GAA':'E', 'GAG': 'E', 'TGT':'C', 'TGC': 'C',
    'TGA': 'Stop', 'TGG': 'W', 'CGT': 'R', 'CGC': 'R', 'CGA':'R', 'CGG':'R', 'AGT':'S','AGC':'S', 'AGA':'R',
    'AGG':'R', 'GGT':'G', 'GGC':'G','GGA':'G','GGG':'G'}
    
    for x in range(0,len(sequence)-2):
        codon=sequence[x]+sequence[x+1]+sequence[x+2]
        translation+=acids.get(codon)
        
    return translation
    
def translate2(sequence):
    translation=''
    acids ={'TTT': 'Phe', 'TTC': 'Phe', 'TTA': 'Leu', 'TTG': 'Leu', 'CTT': 'Leu', 'CTC': 'Leu', 'CTA':'Leu','CTG':'Leu',
    'ATT': 'Ile','ATC':'Ile','ATA':'Ile', 'ATG':'Met', 'GTT': 'Val', 'GTC': 'Val', 'GTA':'Val', 'GTG':'Val','TCT':'Ser',
    'TCC':'Ser','TCA':'Ser','TCG':'Ser','CCT':'Pro', 'CCC':'Pro','CCA':'Pro', 'CCG':'Pro', 'ACT':'Thr','ACC':'Thr', 'ACA':'Thr','ACG':'Thr','GCT':'Ala', 'GCC':'Ala','GCA':'Ala',
    'GCG':'Ala','TAT':'Tyr', 'TAC':'Tyr', 'TAA': 'Stop', 'TAG':'Stop', 'CAT':'His','CAC':'His','CAA':'Gln', 'CAG':'Gln',
    'AAT':'Asn', 'AAC':'Asn', 'AAA':'Lys','AAG':'Lys', 'GAT':'Asp','GAC':'Asp', 'GAA':'Glu', 'GAG': 'Glu', 'TGT':'Cys', 'TGC': 'Cys',
    'TGA': 'Stop', 'TGG': 'Trp', 'CGT': 'Arg', 'CGC': 'Arg', 'CGA':'Arg', 'CGG':'Arg', 'AGT':'Ser','AGC':'Ser', 'AGA':'Arg',
    'AGG':'Arg', 'GGT':'Gly', 'GGC':'Gly','GGA':'Gly','GGG':'Gly'}
    
    for x in range(0,len(sequence)-2):
        codon=sequence[x]+sequence[x+1]+sequence[x+2]
        translation+=acids.get(codon)
        
    return translation

def reverseCompliment(sequence):
    reverseC=''    
    compliments={'A': 'T', 'T':'A', 'G':'C','C':'G'}
    reversed=sequence[::-1]
    for n in reversed:
        
        reverseC= reverseC+compliments[n]
    
    return reverseC

def frames(sequence):
    seq=sequence    
    title= "5'3' Frame "
    
    for i in range(0,2):
        for x in range(0, 3):
            print(title+ str((x+1)))
            translation= translate1(seq[x:])
                      
            print (translation)
              
            print()
            print()
        
        title="3'5' Frame "
        seq= reverseCompliment(seq)
    
def main():
    
    infile= sys.argv[1:][0]
    seq= readSeq(infile)
    frames(seq)
    

if __name__ == "__main__":
    main()


#print(translate(readSeq('seq.txt')))

    

                   