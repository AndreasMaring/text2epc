package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import nodes.FlowObject;
import nodes.ProcessEdge;
import nodes.ProcessNode;
import epc.Association;
import epc.Connector;
import epc.Event;
import epc.Function;
import epc.InformationObject;
import epc.SequenceFlow;

public class EPCModel extends ProcessModel {
	
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public enum conType {
		AND, OR, XOR
	}
	
	public EPCModel(){
		super();
	}
	
	public EPCModel(String name) {
        super(name);
    }
	
	public void addFlowObject(FlowObject o) {
        super.addNode(o);
    }
	
	public void addFlow(ProcessEdge e) {
        addEdge(e);
    }
	
	public List<ProcessEdge> getFlows() {
        return getEdges();
    }
	
	public List<FlowObject> getFlowObjects() {
        // Figure out all flow objects
        List<FlowObject> result = new LinkedList<FlowObject>();
        for (ProcessNode n : super.getNodes()) {
            if (n instanceof FlowObject) {
                result.add((FlowObject) n);
            }
        }

        return result;
    }
	
	public List<SequenceFlow> getSequenceFlows() {
        List<SequenceFlow> result = new LinkedList<SequenceFlow>();
        for (ProcessEdge f : super.getEdges()) {
            if (f instanceof SequenceFlow) {
                result.add((SequenceFlow) f);
            }
        }
        return result;
    }
	
	public LinkedList<Association> getAssociations() {
        LinkedList<Association> result = new LinkedList<Association>();
        for (ProcessEdge f : super.getEdges()) {
            if (f instanceof Association) {
                result.add((Association) f);
            }
        }
        return result;
    }
	
	@Override
    public String toString() {
        if (getProcessName() == null) {
            return super.toString();
        }
        return getProcessName() + " (EPC)";
    }

	@Override
    public List<Class<? extends ProcessEdge>> getSupportedEdgeClasses() {
        List<Class<? extends ProcessEdge>> result = new LinkedList<Class<? extends ProcessEdge>>();
        result.add(SequenceFlow.class);
        result.add(Association.class);
        return result;
    }

	@Override
    public List<Class<? extends ProcessNode>> getSupportedNodeClasses() {
        List<Class<? extends ProcessNode>> result = new LinkedList<Class<? extends ProcessNode>>();
        result.add(Event.class);
        result.add(Function.class);
        result.add(InformationObject.class);
        result.add(Connector.class);
        return result;
    }

	@Override
	public String getDescription() {
		return "EPML 2.0";
	}
	
	public void addEvent (Event e){
		events.add(e);
	}
	
	public ArrayList<Event> getEvents (){
		return events;
	}
	

}
