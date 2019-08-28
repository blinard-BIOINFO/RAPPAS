/*
 * Copyright (C) 2019 benjamin.linard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package core.algos;

import core.DNAStatesShifted;
import java.util.Arrays;

/**
 *
 * @author benjamin.linard
 */
public class TEST_SequenceKnife_v2 {
    
    public static void main(String[] args) {
        System.setProperty("debug.verbose", "1");
        int k=4;
        AmbigSequenceKnife sq=new AmbigSequenceKnife(k,k,new DNAStatesShifted(),AmbigSequenceKnife.SAMPLING_LINEAR);
        sq.init("ASSTTATGBCTG");
        System.out.println(sq.getMaxMerCount());
        System.out.println(sq.getMerCount());
        byte[] word=null;
        int counter=0;
        while ((word=sq.getNextByteWord())!=null) {
            System.out.println((counter++)+" #words:"+(word.length/k)+" byte[]="+Arrays.toString(word));
        }
        
        
        
        
    }
    
    
    
}