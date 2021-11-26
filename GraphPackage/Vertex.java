package GraphPackage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import ADTPackage.*; // Classes that implement various ADTs
/**
 A class of vertices for a graph.
 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
class Vertex<T> implements VertexInterface<T>
{
   private T label;
   private LinkedList<Edge> edgeList; // Edges to neighbors
   private boolean visited;                          // True if visited
   private VertexInterface<T> previousVertex;        // On path to this vertex
   private double cost;                              // Of path to this vertex
   
   public Vertex(T vertexLabel)
   {
      label = vertexLabel;
      edgeList = new LinkedList<>();
      visited = false;
      previousVertex = null;
      cost = 0;
   } // end constructor

/* Implementations of the vertex operations go here.
   . . . */

   protected class Edge
   {
      private VertexInterface<T> vertex; // Vertex at end of edge
      private double weight;
      
      protected Edge(VertexInterface<T> endVertex, double edgeWeight)
      {
         vertex = endVertex;
         weight = edgeWeight;
      } // end constructor
      
      protected Edge(VertexInterface<T> endVertex)
      {
         vertex = endVertex;
         weight = 0;
      } // end constructor

      protected VertexInterface<T> getEndVertex()
      {
         return vertex;
      } // end getEndVertex
      
      protected double getWeight()
      {
         return weight; 
      } // end getWeight
   } // end Edge

    public T getLabel() {
        // TODO Auto-generated method stub
        return label;
    }

    public void visit() {
        // TODO Auto-generated method stub
        visited = true;
    }

    public void unvisit() {
        // TODO Auto-generated method stub
        visited = false;
    }

    public boolean isVisited() {
        // TODO Auto-generated method stub
        return visited;
    }

    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
    boolean result = false;

    if (!this.equals(endVertex))
    {  // Vertices are distinct
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        boolean duplicateEdge = false;

        while (!duplicateEdge && neighbors.hasNext())
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (endVertex.equals(nextNeighbor))
                duplicateEdge = true;
        } // end while

        if (!duplicateEdge)
        {
            edgeList.add(new Edge(endVertex, edgeWeight));
            result = true;
        } // end if
    } // end if

    return result;
    } // end connect

    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect


    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;
        
        private NeighborIterator()
        {
            edges = edgeList.iterator();
        } // end default constructor
        
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext
        
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;
            
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();
            
            return nextNeighbor;
        } // end next
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end NeighborIterator

    @Override
    public Iterator<Double> getWeightIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor


    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        // TODO Auto-generated method stub
        previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        // TODO Auto-generated method stub
        return previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        // TODO Auto-generated method stub
        if (previousVertex != null)
            return true;
        return false;
    }

    public void setCost(double newCost) {
        // TODO Auto-generated method stub
        cost = newCost;
    }

    public double getCost() {
        // TODO Auto-generated method stub
        return cost;
    }

    // @author Frank M. Carrano, Timothy M. Henry
    // @version 5.0
    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals
} // end Vertex
