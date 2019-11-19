// import component
import React, { Component } from 'react';
import { View } from 'react-native';
import MultiSelect from 'react-native-multiple-select';

const items = [{
  name: 'Food/Restaurants',
}, {
  name: 'Parks/Amusement Parks',
}, {
  name: 'Tourist Attractions',
}];

export default class ActivitiesPicker extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedItems: [],
    }
  };

  onSelectedItemsChange = selectedItems => {
    this.setState({ selectedItems });
    this.props.setActivitiesFromParent(selectedItems);
  };

  render() {
    const { selectedItems } = this.state;
    return (
      <View style={{ flex: 1, padding: 10 }}>
        <MultiSelect
          hideTags
          items={items}
          uniqueKey="name"
          ref={(component) => { this.multiSelect = component }}
          onSelectedItemsChange={this.onSelectedItemsChange}
          selectedItems={selectedItems}
          selectText="Pick Activities"
          searchInputPlaceholderText="Search Activities..."
          onChangeInput={ (text)=> console.log(text)}
          altFontFamily="ProximaNova-Light"
          tagRemoveIconColor="#CCC"
          tagBorderColor="#CCC"
          tagTextColor="#CCC"
          selectedItemTextColor="#429ef5"
          selectedItemIconColor="#429ef5"
          itemTextColor="#000"
          displayKey="name"
          searchInputStyle={{ color: '#CCC' }}
          submitButtonColor="#429ef5"
          submitButtonText="Done"
        />
      </View>
    );
  }
}
