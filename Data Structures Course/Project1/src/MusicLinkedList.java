import java.util.Iterator;
import java.lang.Math;

public class MusicLinkedList implements MusicList{

	
	private float sampleRate;
	private int numChannels;
	public Node head;
	public Node tail;
	private int numSamples;
	
	public MusicLinkedList(float sampleRate, int numChannels){
		this.numChannels = numChannels;
		this.sampleRate =sampleRate;
		head =null;
		tail=null;
		numSamples=0;
		
	}
	
	private class Node{
		
		private Node nextSample;
		private Node channelPoint;
		private float data;
		
		public Node(float d,Node nS, Node cP){
			data=d;
			nextSample = nS;
			
			channelPoint=cP;
			
			
		}
		
		public float getData(){
			return data;
		}
		public void setData(float f){
			data=f;
		}
		
		public Node nextChannel(){
			return channelPoint;
		}
		public Node nextSample(){
			return nextSample;
		}

		
		public void setNextChannel(Node next) {
			channelPoint= next;
		}
		public void setNextSample(Node next) {
			nextSample = next;
		}
		public boolean hasNext(){
			if(nextSample!=null)
				return true;
			return false;
		}
		
	}
	
	public Node getHead(){
		return head;
	}
	
	@Override
	public int getNumChannels() {
		return numChannels;
	}

	@Override
	public float getSampleRate() {
		return sampleRate;
	}

	@Override
	public int getNumSamples() {
		return numSamples;
	}

	@Override
	public float getDuration() {
		return (float)numSamples/(float)sampleRate;
	}

	@Override
	public void addEcho(float delay, float percent) {
		
		
		Node source;
		Node destination= head;
		int echoSample = (int)Math.floor(delay*sampleRate);
		
		source=head;

		for(int m=0;m<echoSample;m++){
			destination=destination.nextSample();		
		}
		
		float sourceData;
		float destinationData;
		Node baseS;
		Node baseD;
		float sum=0;
		float toAdd;
		for(int x=echoSample; x<numSamples ;x+=echoSample){
			source=head;
			for(int i=0; i<echoSample &&destination!=null;i++){
				baseS=source;
				baseD=destination;
				for(int b=0; b<numChannels ;b++){
					destinationData=destination.getData();
					sourceData=source.getData();
					
					 toAdd= sourceData*percent;				
					sum= toAdd+destinationData;
					
					if(sum>1){
						sum=1;
					}
					else if(sum<-1){
						sum=-1;
					}
					destination.setData(sum);
					
					
					destination=destination.nextChannel();
					source=source.nextChannel();
				}
				
				source=baseS;
				destination=baseD;
				source=source.nextSample();
				destination=destination.nextSample();
			}
			percent=percent*(float).8;
		}	
	}

	@Override
	public void reverse() {
		
		Node current = head;
		head=tail;
		tail =current;
		
		if(numChannels>1){
		for(int i =0; i<numChannels; i++){
			
			reverse(current);
			current= current.nextChannel();
		}
		}
		else{
			reverse(current);
		}
		
	}
	
	private void reverse(Node current){
		Node reversedPart = null;
		while (current != null) {
		    Node next = current.nextSample();
		    current.setNextSample(reversedPart);
		    reversedPart = current;
		    current = next;
		}
		//return reversedPart;
	}

	@Override
	public void changeSpeed(float percentChange) {
		
		float newSampleRate;
		newSampleRate=sampleRate*percentChange;
		sampleRate=newSampleRate;
	}

	@Override
	public void changeSampleRate(float newRate) {
		//System.out.println("hello");
		//float totalTime=0;
		MusicLinkedList newList = new MusicLinkedList(newRate, numChannels);
		double oldTimeBetween = 1/sampleRate;
		double newTimeBetween = 1/newRate;
		double endTime = getDuration();
		Node s1=head;
		Node s2=head.nextSample();
		Node s1Base;
		Node s2Base;
		double s1Data;
		double s2Data;
		double ns;
		
		double currentO1Time=0 ;
		
		double currentO2Time= oldTimeBetween;
		for(double totalTime=0; totalTime<endTime;totalTime+=newTimeBetween){
			
			
			while(currentO2Time<totalTime && s2.hasNext() ){
				currentO1Time+=oldTimeBetween;
				 
				currentO2Time+=oldTimeBetween;
				s1=s1.nextSample();
				 s2=s2.nextSample();
				
			}
			
			s1Base=s1;
			s2Base=s2;
			
			float[] newSample = new float[numChannels];
			
			if(numChannels!=1){
				
				for(int x=0; x<newSample.length;x++){
					
					s1Data=s1.getData();
					s2Data=s2.getData();
					
					ns= s1Data*((currentO2Time-totalTime)/oldTimeBetween) + s2Data*((totalTime-currentO1Time)/oldTimeBetween);
				
					newSample[x]=(float)ns;   //formula
					s1=s1.nextChannel();
					s2=s2.nextChannel();
				}
			}
			else{
				
				s1Data=s1.getData();
				s2Data=s2.getData();
				
				ns= s1Data*((currentO2Time-totalTime)/oldTimeBetween) + s2Data*((totalTime-currentO1Time)/oldTimeBetween);
			
				newSample[0]=(float)ns;   //formula
				
			}
			
			newList.addSample(newSample);
			s1=s1Base;
			s2=s2Base;			
		}
		
		head=newList.head;
		sampleRate=newRate;
	}

	@Override
	public void addSample(float sample) {
		if(numChannels ==1){
			Node newSample = new Node(sample, null, null);
			if(tail==null){
				head=newSample;
			}
			else{
			tail.setNextSample(newSample);
			
			}
			tail =newSample;
			numSamples++;
		}
		else{
			throw new IllegalArgumentException();
		}
		
	}

	
	@Override
	public void addSample(float[] sample) {
		
		if(numChannels == sample.length&& numChannels>1){
			Node currentSample = new Node(sample[0], null,null );
			
			Node prevSample;
			if(tail==null){
				head=tail=currentSample;
				
				for(int i=1; i<sample.length; i++){
					prevSample=currentSample;
					currentSample = new Node(sample[i], null,null);
					prevSample.setNextChannel(currentSample);
				}
				numSamples++;
				
			}
			else{
				tail.setNextSample(currentSample);		
				
				Node nextChannel=tail.nextChannel();
				int i =0;
				while(nextChannel!=null){
					i++;
					currentSample = new Node(sample[i], null,null);
					 
					 nextChannel.setNextSample(currentSample);
					 nextChannel=nextChannel.nextChannel();
				}
				if(numChannels==1){
					
				}
				tail.nextSample().setNextChannel(tail.nextChannel().nextSample());
				Node current=tail.nextChannel();
				while(current!=null){
					if(current.nextChannel()==null){
						break;
					}
					current.nextSample().setNextChannel(current.nextChannel().nextSample());
					current=current.nextChannel();
				}
			
				tail=tail.nextSample();
				numSamples++;
			}
			
			
		}
		else if(numChannels==1 && numChannels==sample.length){
			addSample(sample[0]);
			
		}
			else{
				throw new IllegalArgumentException();
			}
		
	}
	public class ListIterator implements Iterator<float[]> {
		
		private Node current;
		
		public ListIterator(){
			current=head;
		}
		@Override
		public boolean hasNext() {
			if(current!=null){
				return true;
			}
			return false;
		}

		@Override
		public float[] next() {
			Node base=current;
			float[] samples = new float[numChannels];
			if(numChannels!=1){
				for(int i=0; i<numChannels; i++){
					
					samples[i]=current.getData();
					current=current.nextChannel();			
				}
			}
			else{
				samples[0]=current.getData();
			}
			
			current=base.nextSample();
			return samples;
		}
		
	}
	public class ChannelIterator implements Iterator<Float>{

		private Node current;
		
		public ChannelIterator(Node front){
			current= new Node(0, front, null);
			
		}
		@Override
		public boolean hasNext() {
			if(current.nextSample()!=null&&current!=null){
				return true;
			}
			return false;
		}

		@Override
		public Float next() {
			current=current.nextSample();
			return current.getData();
		}
		
	}
	
	@Override
	public Iterator<float[]> iterator() {
		Iterator<float[]> it = new ListIterator();
		return it;
	}

	@Override
	public Iterator<Float> iterator(int channel) {
		Node current;
		current=head;
		
		for(int i=0; i<channel; i++){
			current=current.nextChannel();
		}
		
		Iterator<Float> it = new ChannelIterator(current);
		return it;
	}

	@Override
	public void clip(float startTime, float duration) {
		int startSample= (int) (Math.floor(startTime*sampleRate));
		int endSample= (int)(Math.floor(duration*sampleRate));
		if(startSample<=numSamples){
			Node current =head;
			for(int i =0; i<startSample;i++){
			current=current.nextSample();
			}
			head=current;
			for(int x=0; x<endSample;x++){
				current=current.nextSample();
			}
			tail=current;
			tail.setNextSample(null);
			numSamples=numSamples-(endSample);
		}
		else{
			System.out.println("Clip is past the end of the List");
		}
	}

	@Override
	public void spliceIn(float startSpliceTime, MusicList clipTosplice) {
		MusicLinkedList clipToSplice = (MusicLinkedList) clipTosplice;
		double startSample= Math.floor(startSpliceTime*sampleRate);
		if(clipToSplice.getSampleRate()!=sampleRate){
			clipToSplice.changeSampleRate(sampleRate);
		}
		if(startSample<=numSamples){
			Node current =head;
			for(int i =0; i<startSample;i++){
				current=current.nextSample();
			}
			Node temp;
			Node clipFrontNode= clipToSplice.head;
			Node clipBackNode= clipToSplice.tail;
			for(int z=0; z<numChannels;z++){
			 temp=current.nextSample();
			 current.setNextSample(clipFrontNode);
			 clipBackNode.setNextSample(temp);
			 clipBackNode= clipBackNode.nextChannel();
			 clipFrontNode=clipFrontNode.nextChannel();
			 current=current.nextChannel();
			}
			
			
			numSamples+=clipToSplice.getNumSamples();
		}
	}

	@Override
	public void makeMono(boolean allowClipping) {
		if(allowClipping){
			Iterator<float[]> iter = iterator();
			Node current= head;
			while(iter.hasNext()){
				float[] sample= iter.next();
				float sum=0;
				for(int i=0; i<sample.length;i++){
					sum+=sample[i];
				}
				if(sum>1){
					sum=1;
				}
				else if(sum<-1){
					sum= -1;
				}
				current.setData(sum);
				current.setNextChannel(null);
				current=current.nextSample();
			}
		}
		else{
			Iterator<float[]> iter = iterator();
			Node current= head;
			float largestSum= 0;
			while(iter.hasNext()){
				float[] sample= iter.next();
				float sum=0;
				for(int i=0; i<sample.length;i++){
					sum+=sample[i];
				}
				if(Math.abs((sum))>largestSum){
					largestSum=Math.abs(sum);
				}
				current.setData(sum);
				current=current.nextSample();
			}
			current=head;
			if(largestSum<=1){
				largestSum=1;
			}
			while(current!=null){
				float data = current.getData();
				current.setNextChannel(null);
				current.setData(data/largestSum);	
				current=current.nextSample();
			}
		}
		numChannels=1;
		
	}

	@Override
	public void combine(MusicList clipTocombine, boolean allowClipping) {
		MusicLinkedList clipToCombine = (MusicLinkedList) clipTocombine;
		if(clipToCombine.getNumChannels()==numChannels && clipToCombine.getNumSamples()==numSamples){
			
			if(allowClipping){
				Iterator<float[]> firstIter = iterator();
				Iterator<float[]> secondIter = clipToCombine.iterator();
				Node current=head;
				float[] sample1;
				float[] sample2;
				float[] sum;
				Node base;
				
				while(firstIter.hasNext()){
					sample1 = firstIter.next();
					
					sample2=secondIter.next();
					sum = new float[numChannels];
					base=current;
					
					for(int i=0; i<sample1.length; i++){
						
						sum[i]=sample1[i] +sample2[i];
						
						if(sum[i]>1){
							sum[i]=1;
						}
						else if(sum[i]<-1){
							sum[i]=-1;
						}
						current.setData(sum[i]);
						current=current.nextChannel();
					}
					current=base.nextSample();
				}
			}
			else{
				Iterator<float[]> firstIter = iterator();
				Iterator<float[]> secondIter = clipToCombine.iterator();
				Node current=head;
				
				float[] sample1;
				float[] sample2;
				float[] sum;
				Node base;
				float largestSum=0;
				while(firstIter.hasNext()){
					sample1 = firstIter.next();
					sample2=secondIter.next();
					sum = new float[numChannels];
					for(int i=0; i<sample1.length; i++){
						sum[i]=sample1[i] +sample2[i];
						if(Math.abs(sum[i])>largestSum){
							largestSum=Math.abs(sum[i]);
						}
						
						
					}
				}
				
				if(largestSum<=1){
					largestSum=1;
				}
				base=current;
				while(firstIter.hasNext()){
					sample1 = firstIter.next();
					sample2=secondIter.next();
					sum = new float[numChannels];
					
					for(int i=0; i<sample1.length; i++){
						sum[i]=sample1[i] +sample2[i];
						
						sum[i]=sum[i]/largestSum;
						current.setData(sum[i]);
						current=current.nextChannel();
					}
					current=base.nextSample();
				}
			}
		}
		
		else{
			throw new IllegalArgumentException();
		}
		
		
	}

	@Override
	public MusicLinkedList clone() {
		MusicLinkedList newList = new MusicLinkedList(sampleRate,numChannels);
		Iterator<float[]> iter = iterator();
		while(iter.hasNext()){
			newList.addSample(iter.next());
		}		

		return newList;
	}
	
	
	public void printChannel(Node current){
		
		while(current!=null){
			System.out.print(current.getData() +", ");
			current=current.nextSample();
		}
	}
	
	public void print(){
		Node current=head;
		while(current!=null){
			printChannel(current);
			System.out.println();
			current=current.nextChannel();
		}
	}
	public void printLine(int channel){
	
		Iterator<Float> iter = iterator(channel);
		while(iter.hasNext()){
			
			System.out.println(iter.next());
		}
				
	}
	
	public void printHard(){
		Iterator <float[]> iter = iterator();
		while(iter.hasNext()){
			float[] sample= iter.next();
			for(int i=0;i<sample.length;i++){
			System.out.print(sample[i]+ ", ");
			}
			System.out.println();
		}
	}

//	public static void main( String[] args){
//		MusicLinkedList ml = new MusicLinkedList(1, 3);
//		MusicLinkedList sl = new MusicLinkedList(1,1);
//		MusicLinkedList twol = new MusicLinkedList(1, 3);
//
//		float[] a1 = {(float).02353,(float).06,(float).045};
//		float[] a2 = {(float).0353,(float).34,(float).1};
//		float[] a3 = {(float).2353,(float).6,(float).25};
//		float[] a4 = {(float).01976,(float).044,(float).27};
//		float[] a5 = {(float).16,(float).049,(float).045};
//		
//		float aa1 = (float) 1;
//		float aa2 = (float) 2;
//		float aa3 = (float) .034;
//		float aa4 = (float) .657;
//		float aa5 = (float) .23;
//		
//		float[] b1 = {(float).02353,(float).06,(float).0125};
//		float[] b2 = {(float).053,(float).04,(float).1};
//		float[] b3 = {(float).2353,(float).6,(float).25};
//		float[] b4 = {(float).01976,(float).014,(float).027};
//		float[] b5 = {(float).16,(float).049,(float).035};
//		
//		ml.addSample(a1);
//		ml.addSample(a2);
//		ml.addSample(a3);
//		ml.addSample(a4);
//		ml.addSample(a5);
//		
//		twol.addSample(b1);
//		twol.addSample(b2);
//		twol.addSample(b3);
//		twol.addSample(b4);
//		twol.addSample(b5);
//		
//		
//		
//		
//		//ml.print();
//	//	ml.printHard();
////	ml.reverse();
////		System.out.println();
//	//System.out.println();
//	//twol.print();	
//	
//		System.out.println();
//	//twol.printHard();
//	//System.out.println(ml);
//	//System.out.println(ml.clone());
////	ml.clip((float)1.0001, 3);
//	//ml.combine(twol, true);
////	System.out.println();
//	//ml.makeMono(true);
//	//ml.reverse();
////	ml.clone().printHard();
//	//ml.spliceIn(1,twol);
//	//ml.printHard();
////		ml.reverse();
//	//	System.out.println();
//	//	System.out.println();
//	//	ml.printHard();

//	ml.makeMono(true);
//	//ml.printHard();
//	System.out.println();
//	ml.reverse();
//	System.out.println();
//	ml.printHard();
//	ml.reverse();
//	System.out.println();
//	ml.printHard();
//	System.out.println();
//	ml.reverse();
	//	ml.printHard();
//		sl.addSample(aa1);
//		sl.addSample(aa2);
//	sl.addSample(aa3);
//	//	sl.addSample(aa4);
////		sl.addSample(aa5);
////	
////		
//		sl.printHard();
//		System.out.println();
//		sl.changeSampleRate(2);
////		sl.reverse();
////		System.out.println();
//		sl.printHard();
////		sl.reverse();
////		System.out.println();
////		sl.printHard();
////		System.out.println();
////		sl.reverse();
////			sl.printHard();
////		sl.printLine(1);
////		sl.reverse();
////		System.out.println();
////		sl.printLine(1);
//		
////		System.out.println(l.head.getData());
////		System.out.println(l.tail.getData());
//	//	l.print();
////		l.reverse();
////		System.out.println();
////		l.print();
//		
//	}
//	
	
}
