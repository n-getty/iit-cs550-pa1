#!/usr/bin/python

import sys
import matplotlib.pyplot as plt
import matplotlib
import numpy as np
from math import sqrt

#matplotlib.rcParams['ps.useafm'] = True
#matplotlib.rcParams['pdf.use14corefonts'] = True
#matplotlib.rcParams['text.usetex'] = True

def plot_all():
    sp = []
    ab = []
    
    sp_p = []
    ab_p = []

    i=1

    one_run = np.loadtxt(sys.argv[1])
    sp.append(np.sort(one_run)[:])
    p = 1. * np.arange(len(sp[i-1])) / (len(sp[i-1]) - 1)
    sp_p.append(p)
    
    one_run = np.loadtxt(sys.argv[2])
    ab.append(np.sort(one_run)[:])
    p = 1. * np.arange(len(ab[i-1])) / (len(ab[i-1]) - 1)
    ab_p.append(p)
        
    plt.figure()
    font = {'size':'18'}
    matplotlib.rc('font', **font)
    
    color = ('b','r','g','c','m','y','k','b','g')
        
    for i in range(0,len(ab)):
        plt.plot(ab[i], ab_p[i],
                 color=color[i],
                 label=('concurrent  updates (2)'),
                 linestyle='solid')
    for i in range(0,len(sp)):
        plt.plot(sp[i], sp_p[i],
                 color=color[i+1],
                 label=('non-concurrent updates'),
                 linestyle='dashed')
        
    plt.grid(True)
    plt.legend(loc='lower right')#upper left')

    plt.xlim([500000, 25000000])
    #labels = plt.get_xticks().tolist()
    labels = (range(0, 25000001, 5000000))

    plt.xticks(labels)
    #plt.yticks(range(0, int(bw) + 1, 100))
    
    plt.ylim([0, 1])
    plt.xlabel('Time to request and download 1MB file Time in (Seconds/100)', fontsize=20)
    plt.ylabel('Fraction of Trials', fontsize=20)
    plt.show()
    plt.savefig(output_file, format='eps')
    
if __name__ == '__main__':
    output_file = sys.argv[3]
    plot_all()
                     
