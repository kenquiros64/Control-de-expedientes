class AddColumnToCita < ActiveRecord::Migration
  def change
    add_column :cita, :fecha, :date
    add_column :cita, :hora, :time
  end
end
