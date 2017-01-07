class AddColumnToObservacion < ActiveRecord::Migration
  def change
    add_reference :observacions, :medico, index: true, foreign_key: true
  end
end
