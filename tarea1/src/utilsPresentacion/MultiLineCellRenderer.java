package utilsPresentacion;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setAlignmentY(TOP_ALIGNMENT);
        setAlignmentX(CENTER_ALIGNMENT);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((String) value);
        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        return this;
    }
}