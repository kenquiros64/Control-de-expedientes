class Citum < ActiveRecord::Base
  belongs_to :medico
  belongs_to :paciente
  has_and_belongs_to_many :observacions, dependent: :destroy, :join_table => :observacions_citas
  
  validates_presence_of :fecha , :on => [ :create, :update ],:message => "no puede estar vacío"
  validates_presence_of :hora ,:on => [ :create, :update ], :message => "no puede estar vacío"
  
  accepts_nested_attributes_for :observacions, reject_if: :all_blank, allow_destroy: true
end
